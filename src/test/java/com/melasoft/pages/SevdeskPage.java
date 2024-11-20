package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class SevdeskPage extends BasePage{


    @FindBy(xpath = "//div[@id='w-node-_8d3b1c28-f4e0-a27f-33f7-3744da6205dd-da620445']//h3[.='Rechnung']")
    public WebElement rechnungProductType;

    @FindBy(xpath = "(//div[.='Perfekt für alle, die Rechnungen schreiben – schnell und einfach.\u2028'])[3]//..//..//..//div[@class='c-new-price-text']")
    public WebElement rechnungPrice;

    @FindBy(xpath ="(//div[.='Unsere Empfehlung']//..//h3[.='Buchhaltung'])[3]\n")
    public WebElement buchhaltungProductType;

    @FindBy(xpath = "(//div[.='Unsere Empfehlung']//..//h3[.='Buchhaltung'])[3]//..//..//div[@class='c-new-price-text']")
    public WebElement buchhaltungPrice;


    @FindBy(xpath ="(//h3[.='Buchhaltung Pro'])[3]")
    public WebElement buchhaltungProProductType;


    @FindBy(xpath = "(//h3[.='Buchhaltung Pro'])[3]//..//..//div[@class='c-new-price-text']")
    public WebElement buchhaltungProPrice;

    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        String[] productTypes = {"Rechnung", "Buchhaltung", "Buchhaltung Pro"};
        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        Map<String, WebElement[]> productElements = new HashMap<>();
        productElements.put("Rechnung", new WebElement[]{rechnungProductType, rechnungPrice});
        productElements.put("Buchhaltung", new WebElement[]{buchhaltungProductType, buchhaltungPrice});
        productElements.put("Buchhaltung Pro", new WebElement[]{buchhaltungProProductType, buchhaltungProPrice});


        for (String productType : productTypes) {
            WebElement[] elements = productElements.get(productType);

            if (elements != null) {

                js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);
                String productName = elements[0].getText().trim();
                String productPrice = elements[1].getText().trim();


                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found for: " + productType);
                } else {

                    System.out.println(productName + " PRICE: " + productPrice );
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found at index: " + productType);
            }
        }


        CsvUtil.createCsvFile(pricingDetails);
    }


}
