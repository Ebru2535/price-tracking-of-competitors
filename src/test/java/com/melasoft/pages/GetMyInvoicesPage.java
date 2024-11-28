package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class GetMyInvoicesPage extends BasePage{


    @FindBy(xpath = "//button[@title='Accept all cookies']")
    public WebElement acceptAllCookies;

    @FindBy(xpath = "//div[@data-id='d2a7cf6']//h4")
    public WebElement essential;


    @FindBy(xpath = "(//span[@data-fallback-field-name='preis'])[1]")
    public WebElement essentialPrice;

    @FindBy(xpath = "(//div[@class='elementor-widget-container']//h4[.='Small'])[1]")
    public WebElement small;


    @FindBy(xpath = "(//span[@data-fallback-field-name='preis'])[2]")
    public WebElement smallPrice;

    @FindBy(xpath = "(//div[@class='elementor-widget-container']//h4[.='Standard'])[1]")
    public WebElement standard;


    @FindBy(xpath = "(//span[@data-fallback-field-name='preis'])[3]")
    public WebElement standardPrice;

    @FindBy(xpath = "(//h4[.='Professional'])[1]")
    public WebElement professional;


    @FindBy(xpath = "(//span[@data-field-name='preis_black_week'])[1]")
    public WebElement professionalPrice;

    @FindBy(xpath = "(//h4[.='Enterprise'])[1]")
    public WebElement enterprise;


    @FindBy(xpath = "(//span[@id='country_specific_content_6747018c19013'])[1]")
    public WebElement enterprisePrice;

    @FindBy(xpath = "(//ul//li//a[.='Preise'])[4]")
    public WebElement preseMenu;

    public void extractPricingDetails(String url) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        // Product types and their corresponding WebElements
        Map<String, WebElement[]> productElements = new HashMap<>();
        productElements.put("Essential", new WebElement[]{essential, essentialPrice});
        productElements.put("Small", new WebElement[]{small, smallPrice});
        productElements.put("Standard", new WebElement[]{standard, standardPrice});
        productElements.put("Professional", new WebElement[]{professional, professionalPrice});
        productElements.put("Enterprise", new WebElement[]{enterprise, enterprisePrice});

        Map<String, String> pricingDetails = new HashMap<>();
        pricingDetails.put("url" , url);

        for (Map.Entry<String, WebElement[]> entry : productElements.entrySet()) {
            String productType = entry.getKey();
            WebElement[] elements = entry.getValue();

            if (elements != null) {
                try {

               ;
                    js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);

                    String productName = elements[0].getText().trim();
                    String productPrice = elements[1].getText().trim();

                    if (productName.isEmpty() || productPrice.isEmpty()) {
                        System.out.println("Product or price information not found for: " + productType);
                    } else {
                        System.out.println(productName + " PRICE: " + productPrice);
                        pricingDetails.put(productName, productPrice);
                    }
                } catch (Exception e) {
                    System.out.println("Error retrieving data for: " + productType + " - " + e.getMessage());
                }
            } else {
                System.out.println("Product or price element not found for: " + productType);
            }
        }


        CsvUtil.createCsvFile(pricingDetails);
    }








}
