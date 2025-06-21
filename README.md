# E-Commerce_Autoamtion
# 🛒 Flipkart Mobile Search Automation Suite

This project automates the process of searching for mobile phones on [Flipkart](https://www.flipkart.com), applying filters (price and OS), sorting by "Newest First", extracting mobile names and prices, and exporting the data to an Excel file.

Built using **Java**, **Selenium WebDriver**, **TestNG**, and **Apache POI**, it includes cross-browser testing support (Chrome and Edge) and generates structured Excel output with validations.

---

## 🚀 Features

- ✅ Launch Flipkart and close login popup if present  
- 🔍 Search for **mobiles** and select the second auto-suggestion  
- 💰 Apply **price filter** using a slider  
- 📱 Apply **OS filter** by selecting "Pie"  
- 🆕 Sort results by **Newest First**  
- 📝 Extract top 5 mobile names and prices  
- ✅ **Validate** each price is under ₹15,000  
- 📤 Export results to **Excel file** (`.xlsx`) with:
  - Mobile Name
  - Price (INR)
  - Pass/Fail status if the first mobile's price < ₹30,000

---

## 🧰 Tech Stack

| Purpose           | Technology                  |
|------------------|------------------------------|
| Programming      | Java 11                      |
| Automation       | Selenium WebDriver 4         |
| Testing Framework| TestNG                       |
| Reporting        | Console Logs + Excel Export  |
| Excel Handling   | Apache POI                   |
| Driver Mgmt      | WebDriverManager             |
| Build Tool       | Maven                        |
| Browsers         | Chrome, Edge (cross-browser) |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 11+
- Maven installed and configured
- ChromeDriver and/or EdgeDriver (auto-managed by WebDriverManager)
- Internet connection (for WebDriverManager to fetch drivers)


---

## 🧪 Run the Test

Use Maven to execute the suite across browsers (Chrome and Edge):

```bash
mvn clean test
📦 Output Sample (Excel)
Mobile Name	Price (INR)	First Mobile Price < ₹30000 Status
Samsung Galaxy M14	₹12,999	PASS - ₹12,999 (less than ₹30000)
Redmi 12 5G	₹11,499	
realme Narzo N55	₹10,499	
Infinix Zero 5G	₹13,999	
Motorola G32	₹9,999
