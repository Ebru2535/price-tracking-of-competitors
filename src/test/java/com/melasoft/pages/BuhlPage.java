package com.melasoft.pages;


import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BuhlPage extends BasePage {

    @FindBy(id = "usercentrics-root")
    private WebElement shadowHost;

public WebElement getAcceptAllButton() {
    WebElement shadowHost = Driver.getDriver().findElement(By.id("usercentrics-cmp-ui"));
    SearchContext shadowRoot = (SearchContext) ((JavascriptExecutor) Driver.getDriver())
            .executeScript("return arguments[0].shadowRoot;", shadowHost);
    return shadowRoot.findElement(By.cssSelector("[id='accept']"));
}

    @FindBy(xpath = "//div[.='XS']")
    public WebElement xsProductType;

    @FindBy(xpath = "(//div[@data-discount='0']/./span)[1]")
    public WebElement xsPrice;

    @FindBy(xpath = "//div[.='S']")
    public WebElement sProductType;

    @FindBy(xpath = "(//div[@data-discount='0']/./span)[2]")
    public WebElement sPrice;

    @FindBy(xpath = "//div[.='M']")
    public WebElement mProductType;

    @FindBy(xpath = "(//div[@data-discount='0']/./span)[3]")
    public WebElement mPrice;

    @FindBy(xpath = "(//div[.='L'])[1]")
    public WebElement lProductType;

    @FindBy(xpath = "(//div[@data-discount='0']/./span)[4]")
    public WebElement lPrice;

    @FindBy(xpath = "//div[.='XS']")
    public WebElement freeXsHeader;

    @FindBy(xpath = "//div[.='S']")
    public WebElement smHeader;

    @FindBy(xpath = "//ul//li[.='L']")
    public WebElement lHeader;

/*
    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        WebElement[] productHeaders = {freeXsHeader, smHeader, lHeader};
        WebElement[] productTypes = {xsProductType, sProductType, mProductType, lProductType};
        WebElement[] productPrices = {xsPrice, sPrice, mPrice, lPrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url", url);

        for (int i = 0; i < productHeaders.length; i++) {
            try {
          //      WebElement headerElement = productHeaders[i];
                WebElement productTypeElement = productTypes[i];
                WebElement productPriceElement = productPrices[i];
                if (i == productHeaders.length - 1) {
                    System.out.println("Processing last element at index: " + i);
                    // Son elemana özel işlem (isteğe bağlı)
                }
                wait.until(ExpectedConditions.visibilityOf(productTypeElement));
                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();
               // String productPrice = productPriceElement.getAttribute("textContent").trim();

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found for header at index: " + i);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice + "£");
                    pricingDetails.put(productName, productPrice);
                }
            } catch (Exception e) {
                System.out.println("An error occurred while processing index: " + i);
                e.printStackTrace();
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }

*/
public void extractPricingDetails(String url) {
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    WebElement[] productHeaders = {freeXsHeader, smHeader, lHeader};
    WebElement[] productTypes = {xsProductType, sProductType, mProductType, lProductType};
    WebElement[] productPrices = {xsPrice, sPrice, mPrice, lPrice};

    Map<String, String> pricingDetails = new HashMap<>();
    pricingDetails.put("url", url);

    // Dizilerin en küçük uzunluğuna göre işlem yapıyoruz
    int length = Math.min(Math.min(productHeaders.length, productTypes.length), productPrices.length);

    for (int i = 0; i < length; i++) {
        try {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];


            if (i == length - 1) {
                System.out.println("Processing last element at index: " + i);
            }


            js.executeScript("arguments[0].scrollIntoView(true);", productPriceElement);
            wait.until(ExpectedConditions.visibilityOf(productTypeElement));

            String productName = productTypeElement.getText().trim();
            String productPrice = productPriceElement.getAttribute("textContent").trim();


            System.out.println("Product Type: " + productName + " | Product Price: " + productPrice);

            if (productName.isEmpty() || productPrice.isEmpty()) {
                System.out.println("Product or price information not found for header at index: " + i);
            } else {
                System.out.println(productName + " PRICE: " + productPrice + "£");
                pricingDetails.put(productName, productPrice);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing index: " + i);
            e.printStackTrace();
        }
    }


    CsvUtil.createCsvFile(pricingDetails);
}





}



