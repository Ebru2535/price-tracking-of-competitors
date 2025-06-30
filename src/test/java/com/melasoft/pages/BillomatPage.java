package com.melasoft.pages;

import com.melasoft.utilities.BrowserUtils;
import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BillomatPage extends BasePage {


    @FindBy(id = "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
    public WebElement shadowHost;


    @FindBy(xpath = "((//p[@class='name'])//b)[1]")
    public WebElement professional;

    @FindBy(xpath = "(//span[@class='price']//strong)[2]")
    public WebElement professionalPrice;

    @FindBy(xpath = "((//p[@class='name'])//b)[2]")
    public WebElement business;

    @FindBy(xpath = "(//span[@class='price']//strong)[4]")
    public WebElement businessPrice;

    @FindBy(xpath = "((//p[@class='name'])//b)[3]")
    public WebElement enterprise;

    @FindBy(xpath = "(//span[@class='price']//strong)[6]")
    public WebElement enterprisePrice;


    public void extractPricingDetails(String url) {

        BrowserUtils.sleep(5);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        WebElement[] productTypes = {professional, business, enterprise};
        WebElement[] productPrices = {professionalPrice, businessPrice, enterprisePrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url", url);

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {

                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productTypeElement);

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

                // Debugging: Check HTML source
            //    System.out.println("Product Type HTML: " + productTypeElement.getAttribute("outerHTML"));
            //    System.out.println("Product Price HTML: " + productPriceElement.getAttribute("outerHTML"));


                // Safe check for space character before using substring
                int spaceIndex = productPrice.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex < productPrice.length() - 1) {
                    productPrice = productPrice.substring(spaceIndex).trim();
                } else {
                    System.out.println("Space character not found, full price will be used: '" + productPrice + "'");
                    productPrice = productPrice.trim(); // Or keep it as is
                }

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information is missing: " + productName + " / " + productPrice);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice);
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found at index: " + i);
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }




    /*

    public void extractPricingDetails(String url) {

        BrowserUtils.sleep(5);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        WebElement[] productTypes = {professional, business, enterprise};
        WebElement[] productPrices = {professionalPrice, businessPrice, enterprisePrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url", url);

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {

                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", productTypeElement);

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

                // Debugging: Check HTML source
                System.out.println("Product Type HTML: " + productTypeElement.getAttribute("outerHTML"));
                System.out.println("Product Price HTML: " + productPriceElement.getAttribute("outerHTML"));

                // Remove unnecessary parts like 'ab' and '*' from the price
                if (productPrice.contains("ab")) {
                    productPrice = productPrice.replace("ab", "").trim(); // Remove "ab"
                }
                if (productPrice.contains("*")) {
                    productPrice = productPrice.replace("*", "").trim(); // Remove "*"
                }

                // Safe check for space character before using substring
                int spaceIndex = productPrice.indexOf(" ");
                if (spaceIndex != -1 && spaceIndex < productPrice.length() - 1) {
                    productPrice = productPrice.substring(spaceIndex).trim();
                } else {
                    System.out.println("Space character not found, full price will be used: '" + productPrice + "'");
                    productPrice = productPrice.trim(); // Or keep it as is
                }

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information is missing: " + productName + " / " + productPrice);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice);
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found at index: " + i);
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }
     */



}