package com.melasoft.pages;

import com.melasoft.utilities.Driver;
import com.melasoft.utilities.CsvUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZervantPage extends BasePage{
    WebDriver driver;




    @FindBy(xpath = "//div[@class='pricing']")
    public List<WebElement> priceElements;

    @FindBy(xpath = "(//button[.='Accept all'])[1]")
    public WebElement acceptAllButton;

    @FindBy(xpath = "//h3[.='Starter']")
    public WebElement starterProductType;

    @FindBy(xpath = "(//span[@class='price price--gbp yearly-price is-size-2 is-inline'])[2]")
    public WebElement starterPrice;

    @FindBy(xpath = "//h3[.='Pro']")
    public WebElement proProductType;

    @FindBy(xpath = "(//span[@class='price price--gbp yearly-price is-size-2 is-inline'])[3]")
    public WebElement proPrice;

    @FindBy(xpath = "//h3[.='Growth']")
    public WebElement growthProductType;

    @FindBy(xpath = "(//span[@class='price price--gbp yearly-price is-size-2 is-inline'])[4]")
    public WebElement growthPrice;


    public void extractPricingDetailsForSunalr() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        // Ordered product types and prices
        WebElement[] productTypes = {starterProductType, proProductType, growthProductType};
        WebElement[] productPrices = {starterPrice, proPrice, growthPrice};

        Map<String, String> pricingDetails = new HashMap<>();

        for (int i = 0; i < productTypes.length; i++) {
            WebElement productTypeElement = productTypes[i];
            WebElement productPriceElement = productPrices[i];


            js.executeScript("arguments[0].scrollIntoView(true);", productTypeElement);


            String productName = productTypeElement.getText().trim();
            String productPrice = productPriceElement.getText().trim();


            if (productName.isEmpty() || productPrice.isEmpty()) {
                System.out.println("Product or price information not found for: " + productName);
            } else {

          //      System.out.println(productName + " PRICE: " + productPrice + " £");
                pricingDetails.put(productName, productPrice);
            }
        }


        CsvUtil.createCsvFile(pricingDetails);
    }











}
