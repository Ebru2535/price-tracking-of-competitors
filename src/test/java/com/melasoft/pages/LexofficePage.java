package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class LexofficePage extends BasePage{


    @FindBy(id = "usercentrics-root")
    private WebElement shadowHost;


    public WebElement getAcceptAllButton() {
        return (WebElement) ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "return arguments[0].shadowRoot.querySelector(\"[data-testid='uc-accept-all-button']\");",
                shadowHost
        );
    }

    @FindBy(xpath = "//li[@class='offer offer-s boxPadding boxShadow borderRadius']//h2")
    public  WebElement s;

    @FindBy(xpath = "(//li[@class='offer offer-s boxPadding boxShadow borderRadius']//h2//..//p)[3]")
    public WebElement sPrice;

    @FindBy(xpath = "//li[@class='offer offer-m boxPadding boxShadow borderRadius']//h2")
    public  WebElement m;

    @FindBy(xpath = "(//li[@class='offer offer-m boxPadding boxShadow borderRadius']//h2//..//p)[3]")
    public WebElement mPrice;

    @FindBy(xpath = "//li[@class='offer offer-l boxPadding boxShadow borderRadius']//h2")
    public  WebElement l;

    @FindBy(xpath = "(//li[@class='offer offer-l boxPadding boxShadow borderRadius']//h2//..//p)[4]")
    public WebElement lPrice;

    @FindBy(xpath = "//li[@class='offer offer-xl boxPadding boxShadow borderRadius']//h2")
    public  WebElement xl;

    @FindBy(xpath = "(//li[@class='offer offer-xl boxPadding boxShadow borderRadius']//h2//..//p)[3]")
    public WebElement xlPrice;

    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // Product types and their corresponding WebElements
        Map<String, WebElement[]> productElements = new HashMap<>();
        productElements.put("Rechnung", new WebElement[]{s, sPrice});
        productElements.put("Buchhaltung", new WebElement[]{m, mPrice});
        productElements.put("Buchhaltung Pro", new WebElement[]{l, lPrice});
        productElements.put("Buchhaltung XL", new WebElement[]{xl, xlPrice});

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        for (Map.Entry<String, WebElement[]> entry : productElements.entrySet()) {
            String productType = entry.getKey();
            WebElement[] elements = entry.getValue();

            if (elements != null) {
                js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);
                String productName = elements[0].getText().trim();
                String productPrice = elements[1].getText().trim();

                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Product or price information not found for: " + productType);
                } else {
                    System.out.println(productName + " PRICE: " + productPrice);
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Product or price element not found for: " + productType);
            }
        }

        CsvUtil.createCsvFile(pricingDetails);
    }







}
