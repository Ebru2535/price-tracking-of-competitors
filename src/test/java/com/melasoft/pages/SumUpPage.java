package com.melasoft.pages;

import com.melasoft.utilities.BrowserUtils;
import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.HashMap;
import java.util.Map;

public class SumUpPage extends BasePage {

    @FindBy(xpath = "//h4[@id='solo-lite']")
    public WebElement soloLite;

    @FindBy(xpath = "(//h4[@id='solo-lite']//..//p//span)[2]")
    public WebElement soloLitePrice;

    @FindBy(xpath = "//h4[@id='solo']")
    public WebElement solo;

    @FindBy(xpath = "(//h4[@id='solo']//..//p//span)[2]")
    public WebElement soloPrice;

    @FindBy(xpath = "//h4[@id='kassensystem-lite']")
    public WebElement kassensystenLite;

    @FindBy(xpath = "(//h4[@id='kassensystem-lite']//..//p//span)[2]")
    public WebElement kassensystenLitePrice;

    @FindBy(xpath = "//p[.='Kassensystem']")
    public WebElement kassensystem;

    @FindBy(xpath = "(//th[@id='cui-ct-headers-2KVaLi3gCvQq9O2mmCPaTk']/./././/p)[1]")
    public WebElement kassensystemPro;

    @FindBy(xpath = "(//p[@class='cui-body-o5xe cui-body-s-lehd cui-body-regular-hd7o cui-body-normal-pvqj cui-tableheader-description-nupr'])[4]")
    public WebElement kassensystemPrice;


    @FindBy(xpath = "(//p[@class='cui-body-o5xe cui-body-s-lehd cui-body-regular-hd7o cui-body-normal-pvqj cui-tableheader-description-nupr'])[5]")
    public WebElement kassensystemProPrice;


    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        WebElement[] productTypes = {kassensystem, kassensystemPro};
        WebElement[] productPrices = {kassensystemPrice, kassensystemProPrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url", url);

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {
                js.executeScript("arguments[0].scrollIntoView(true);", productTypeElement);
                BrowserUtils.sleep(1);

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    BrowserUtils.sleep(5);
                    System.out.println("Product or price information not found for: " + productName);
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


}
