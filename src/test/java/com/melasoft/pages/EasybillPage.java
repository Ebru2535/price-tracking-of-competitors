package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class EasybillPage extends BasePage{

    @FindBy(xpath = "(//h3[.='Starter'])[1]")
    public WebElement basicProductType;

    @FindBy(xpath = "(//span[@class='e_price-counter'])[2]")
    public WebElement priceBasic;


    @FindBy(xpath = "(//h3[.='Professional'])[1]")
    public WebElement plusProductType;

    @FindBy(xpath = "(//span[@class='e_price-counter'])[3]")
    public WebElement pricePlus;


    @FindBy(xpath = "(//h3[.='Premium'])[1]")
    public WebElement businessProductType;

    @FindBy(xpath = "(//span[@class='e_price-counter'])[4]")
    public WebElement priceBusiness;



    @FindBy(id = "usercentrics-root")
    private WebElement shadowHost;


    public WebElement getAcceptAllButton() {
        return (WebElement) ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "return arguments[0].shadowRoot.querySelector(\"[data-testid='uc-accept-all-button']\");",
                shadowHost
        );
    }

    private Map<String, WebElement[]> productElements;


    public EasybillPage() {
        productElements = new HashMap<>();
        initializeMap();
    }

    private void initializeMap() {
        productElements.put("BASIC", new WebElement[]{basicProductType, priceBasic});
        productElements.put("PLUS", new WebElement[]{plusProductType, pricePlus});
        productElements.put("BUSINESS", new WebElement[]{businessProductType, priceBusiness});
    }

 /*   public void extractPricingDetails() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver(); // Obtain JavaScriptExecutor from Driver

        // Ordered product types
        String[] productTypes = {"BASIC", "PLUS", "BUSINESS"};

        for (String productType : productTypes) {
            WebElement[] elements = productElements.get(productType);

            if (elements != null) {
                // Scroll to the product name
                js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);

                // Retrieve product name and price
                String productName = elements[0].getText().trim(); // Product name
                String productPrice = elements[1].getText().trim(); // Product price

                // If product name or price is empty, print an error message
                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found: " + productType);
                } else {
                    // Print output in the desired format
                    System.out.println(productName + " PRICE: " + productPrice + " £");
                }
            } else {
                System.out.println("Product not found: " + productType);
            }
        }
    }

*/



    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // Ordered product types
        String[] productTypes = {"BASIC", "PLUS", "BUSINESS"};
        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        for (String productType : productTypes) {
            WebElement[] elements = productElements.get(productType);

            if (elements != null) {

                js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);
                String productName = elements[0].getText().trim();
                String productPrice = elements[1].getText().trim();


                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found: " + productType);
                } else {

                    System.out.println(productName + " PRICE: " + productPrice + " £");

                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product not found: " + productType);
            }
        }


        CsvUtil.createCsvFile(pricingDetails);
    }

}
