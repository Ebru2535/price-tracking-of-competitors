package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.HashMap;
import java.util.Map;

public class SumUpPage extends BasePage{

    @FindBy(xpath = "//p[@id='solo-lite']")
    public WebElement  soloLite;

    @FindBy(xpath = "(//p[@id='solo-lite']//..//p//span)[2]")
    public WebElement soloLitePrice;

    @FindBy(xpath = "//p[@id='solo']")
    public WebElement  solo;

    @FindBy(xpath = "(//p[@id='solo']//..//p//span)[2]")
    public WebElement soloPrice;

    @FindBy(xpath = "//p[@id='kassensystem-lite']")
    public WebElement  kassensystenLite;

    @FindBy(xpath = "(//p[@id='kassensystem-lite']//..//p//span)[2]")
    public WebElement kassensystenLitePrice;


    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        WebElement[] productTypes = {soloLite, solo, kassensystenLite};
        WebElement[] productPrices = {soloLitePrice, soloPrice, kassensystenLitePrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found for: " + productName);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice );
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found at index: " + i);
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }





}
