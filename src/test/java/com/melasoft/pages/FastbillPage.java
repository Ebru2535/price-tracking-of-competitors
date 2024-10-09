package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class FastbillPage extends BasePage{

    @FindBy (xpath = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")
    public WebElement alleCookiesZulassenButton;

    @FindBy (xpath = "(//div[@class='pricing-column-intro-wrapper'])[1]//h2")
    public WebElement solo;

    @FindBy (xpath = "(//div[@class='pricing-column-discount-wrapper'])[1]")
    public WebElement soloPrice;

    @FindBy (xpath = "(//div[@class='pricing-column-intro-wrapper'])[2]//h2")
    public WebElement plus;


    @FindBy (xpath = "(//div[@class='pricing-column-discount-wrapper'])[2]")
    public WebElement plusPrice;


    @FindBy (xpath = "(//div[@class='pricing-column-intro-wrapper'])[3]//h2")
    public WebElement pro;

    @FindBy (xpath = "(//div[@class='pricing-column-discount-wrapper'])[3]")
    public WebElement proPrice;

    @FindBy (xpath = "(//div[@class='pricing-column-intro-wrapper'])[4]//h2")
    public WebElement premium;
    @FindBy (xpath = "(//div[@class='pricing-column-discount-wrapper'])[4]")
    public WebElement premiumPrice;


    public void extractPricingDetails() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


        String[] productTypes = {"Solo", "Plus", "Pro", "Premium"};
        Map<String, String> pricingDetails = new HashMap<>();

        Map<String, WebElement[]> productElements = new HashMap<>();
        productElements.put("Solo", new WebElement[]{solo, soloPrice});
        productElements.put("Plus", new WebElement[]{plus, plusPrice});
        productElements.put("Pro", new WebElement[]{pro, proPrice});
        productElements.put("Premium", new WebElement[]{premium, premiumPrice});


        for (String productType : productTypes) {
            WebElement[] elements = productElements.get(productType);

            if (elements != null) {

                js.executeScript("arguments[0].scrollIntoView(true);", elements[0]);
                String productName = elements[0].getText().trim();
                String productPrice = elements[1].getText().trim();


                if (productName.isEmpty() || productPrice.isEmpty()) {
                    System.out.println("Ürün ya da fiyat bilgisi bulunamadı: " + productType);
                } else {

                    System.out.println(productName + " FİYAT: " + productPrice + " £");
                    pricingDetails.put(productName, productPrice);
                }
            } else {
                System.out.println("Ürün bulunamadı: " + productType);
            }
        }


        CsvUtil.createCsvFile(pricingDetails);
    }



}
