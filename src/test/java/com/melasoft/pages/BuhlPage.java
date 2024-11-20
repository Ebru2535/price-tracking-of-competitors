package com.melasoft.pages;


import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
        return (WebElement) ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "return arguments[0].shadowRoot.querySelector(\"[data-testid='uc-accept-all-button']\");",
                shadowHost
        );
    }



    @FindBy(xpath = "(//div[@class='plan'])[2]")
    public WebElement xsProductType;

    @FindBy(xpath = "(//div[@class='preis-wrap__discounted'])[2]")
    public WebElement xsPrice;

    @FindBy(xpath = "(//div[@class='plan'])[3]")
    public WebElement sProductType;

    @FindBy(xpath = "(//div[@class='preis-wrap__discounted'])[3]")
    public WebElement sPrice;

    @FindBy(xpath = "(//div[@class='plan'])[4]")
    public WebElement mProductType;

    @FindBy(xpath = "(//div[@class='preis-wrap__discounted'])[4]")
    public WebElement mPrice;

    @FindBy(xpath = "(//div[@class='plan'])[5]")
    public WebElement lProductType;

    @FindBy(xpath = "(//div[@class='preis-wrap__discounted'])[5]")
    public WebElement lPrice;
    @FindBy(xpath = "//ul//li[.='Free,XS']")
    public WebElement freeXsHeader;

    @FindBy(xpath = "//ul//li[.='S,M']")
    public WebElement smHeader;
    @FindBy(xpath = "//ul//li[.='L']")
    public WebElement lHeader;


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
                WebElement headerElement = productHeaders[i];
                WebElement productTypeElement = productTypes[i];
                WebElement productPriceElement = productPrices[i];

                if (headerElement == null || productTypeElement == null || productPriceElement == null) {
                    System.out.println("Header, product type, or price element is missing at index: " + i);
                    continue;
                }

                js.executeScript("arguments[0].scrollIntoView(true);", headerElement);

                try {
                    wait.until(ExpectedConditions.elementToBeClickable(headerElement)).click();
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Element click intercepted. Trying JS click...");
                    js.executeScript("arguments[0].click();", headerElement);
                }

                wait.until(ExpectedConditions.visibilityOf(productTypeElement));

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

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



