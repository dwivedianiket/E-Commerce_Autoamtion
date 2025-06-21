
package EcomAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FlipkartMobileSearchTest {
    WebDriver driver;
    FlipkartPage flipkartPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        switch(browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Invalid Browser");
                throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        flipkartPage = new FlipkartPage(driver);
    }

    @Test
    public void testMobileSearchFlow() throws InterruptedException, IOException {
        driver.get("https://www.flipkart.com/");
        flipkartPage.closeLoginPopup();
        flipkartPage.searchMobiles();
        flipkartPage.applyPriceFilter();
        flipkartPage.applyOSFilter();
        flipkartPage.sortByNewest();

        List<WebElement> names = flipkartPage.getMobileNames();
        List<WebElement> prices = flipkartPage.getMobilePrices();

        int count = Math.min(5, Math.min(names.size(), prices.size()));
        System.out.println("Top " + count + " Mobiles:");

        ExcelWriter excelWriter = new ExcelWriter("Mobile Data");
        String[] headers = {"Mobile Name", "Price (INR)", "First Mobile Price < ₹30000 Status"};
        excelWriter.writeHeader(headers);

        for (int i = 0; i < count; i++) {
            String name = names.get(i).getText();
            String priceText = prices.get(i).getText().replace("₹", "").replace(",", "").trim();
            int price = Integer.parseInt(priceText);
            System.out.println((i + 1) + ". " + name + " - ₹" + price);
            Assert.assertTrue(price <= 15000, "Mobile price exceeds ₹15000");

            excelWriter.writeMobileData(i + 1, name, price);
        }

        String firstPriceText = prices.get(0).getText().replace("₹", "").replace(",", "").trim();
        int firstPrice = Integer.parseInt(firstPriceText);

        String firstPriceStatus;
        if (firstPrice < 30000) {
            firstPriceStatus = "PASS - ₹" + firstPrice + " (less than ₹30000)";
            System.out.println("First mobile price is less than ₹30000: ₹" + firstPrice);
        } else {
            firstPriceStatus = "FAIL - ₹" + firstPrice + " (not less than ₹30000)";
            System.out.println("First mobile price is ₹" + firstPrice + ", which is not less than ₹30000");
        }

        excelWriter.writeFirstMobilePriceStatus(1, firstPriceStatus);

        Assert.assertTrue(firstPrice < 30000, "First Mobile Price is not less than ₹30000");

        excelWriter.autoSizeColumns();

        try {
            excelWriter.save("FlipkartMobileData_" + System.currentTimeMillis() + ".xlsx");
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            excelWriter.close();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
