package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class BillomatPage extends BasePage{


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

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // Ordered product types and corresponding prices
        WebElement[] productTypes = {professional, business, enterprise};
        WebElement[] productPrices = {professionalPrice, businessPrice, enterprisePrice};

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];

            if (productTypeElement != null && productPriceElement != null) {
                // Scroll the element into view
                //  js.executeScript("arguments[0].scrollIntoView(true);", productTypeElement);

                String productName = productTypeElement.getText().trim();
                String productPrice = productPriceElement.getText().trim();
                productPrice=productPrice.substring(productPrice.indexOf(" "));

                if (productName.isEmpty() || productPrice.isEmpty()) {
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
