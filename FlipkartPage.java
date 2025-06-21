package EcomAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class FlipkartPage {
    WebDriver driver;
    WebDriverWait wait;

    public FlipkartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void closeLoginPopup() {
        try {
            List<WebElement> closeButtons = driver.findElements(By.cssSelector("button._2KpZ6l._2doB4z"));
            if (!closeButtons.isEmpty()) {
                closeButtons.get(0).click();
            }
        } catch (Exception e) {
            System.out.println("Popup close failed: " + e.getMessage());
        }
    }

    public void searchMobiles() {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys("mobiles ");
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[2]//div[1]//a[1]//div[2]")));
        suggestion.click();
    }

    public void applyPriceFilter() throws InterruptedException {
        WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='iToJ4v D0puJn']//div[@class='PYKUdo']")));
        new Actions(driver).dragAndDropBy(slider, -180, 0).perform();
        Thread.sleep(2000);
    }

    public void applyOSFilter() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0, 2200)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'Operating System Version Name')]")).click();

        driver.findElement(By.xpath("//span[normalize-space()='12 MORE']")).click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 150)", "");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@title='Pie']//div[@class='XqNaEv']")).click();
        Thread.sleep(2000);
    }

    public void sortByNewest() throws InterruptedException {
        WebElement sortOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Newest First']")));
        sortOption.click();
        Thread.sleep(3000);
    }

    public List<WebElement> getMobileNames() {
        return driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
    }

    public List<WebElement> getMobilePrices() {
        return driver.findElements(By.xpath("//div[@class='Nx9bqj _4b5DiR']"));
    }
}


