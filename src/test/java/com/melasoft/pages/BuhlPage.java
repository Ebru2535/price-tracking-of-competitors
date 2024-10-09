package com.melasoft.pages;


import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public void extractPricingDetails() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        WebElement[] productTypes = {xsProductType, sProductType, mProductType, lProductType};
        WebElement[] productPrices = {xsPrice, sPrice, mPrice, lPrice};

        Map<String, String> pricingDetails = new HashMap<>();

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {

              //  js.executeScript("arguments[0].scrollIntoView(true);", productTypeElement);

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found for: " + productName);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice + " £");
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found at index: " + i);
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }





}



