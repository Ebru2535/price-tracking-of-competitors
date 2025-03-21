package com.melasoft.step_definition;

import com.melasoft.pages.*;
import com.melasoft.utilities.BrowserUtils;
import com.melasoft.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;


public class PricingStepDefinitions {
String urlOfWebSite;
    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to(String url) {
        urlOfWebSite=url;
        Driver.getDriver().get(url);
    }

    @When("^I extract easybill pricing details$")
    public void i_extract_easybill_pricing_details() {

        EasybillPage easybillPage = new EasybillPage();
        BrowserUtils.sleep(5);
        easybillPage.getAcceptAllButton().click();
        easybillPage.extractPricingDetails(urlOfWebSite);
    }

    @When("^I extract zervant pricing details$")
    public void i_extract_zervant_pricing_details() {
        ZervantPage zervantPage=new ZervantPage();
        BrowserUtils.sleep(5);
        zervantPage.acceptAllButton.click();
        zervantPage.extractPricingDetailsForSunalr(urlOfWebSite);

    }

    @When("^I extract meinbuero pricing details$")
    public void i_extract_meinbuero_pricing_details() {
        BuhlPage buhlPage = new BuhlPage();
        BrowserUtils.sleep(2);
        buhlPage.getAcceptAllButton().click();
        buhlPage.extractPricingDetails(urlOfWebSite);

    }

    @When("^I extract billomat pricing details$")
    public void i_extract_billomat_pricing_details() {

        BillomatPage billomatPage = new BillomatPage();
        BrowserUtils.sleep(2);
        billomatPage.shadowHost.click();
        billomatPage.extractPricingDetails(urlOfWebSite);
    }

    @When("^I extract sevdesk pricing details$")
    public void i_extract_sevdesk_pricing_details() {
        SevdeskPage sevdeskPage =new SevdeskPage();
        BrowserUtils.sleep(2);
        sevdeskPage.extractPricingDetails(urlOfWebSite);

    }

    @When("^I extract fastbill pricing details$")
    public void i_extract_fastbill_pricing_details() {
        FastbillPage fastbillPage=new FastbillPage();
        fastbillPage.alleCookiesZulassenButton.click();
        fastbillPage.extractPricingDetails(urlOfWebSite);

    }

    @When("^I extract sumup pricing details$")
    public void i_extract_sumup_pricing_details() {
        SumUpPage sumUpPage = new SumUpPage();
        BrowserUtils.sleep(2);
        sumUpPage.extractPricingDetails(urlOfWebSite);

    }

    @When("^I extract lexoffice pricing details$")
    public void i_extract_lexoffice_pricing_details() {
        LexofficePage lexofficePage=new LexofficePage();
        BrowserUtils.sleep(5);
        lexofficePage.getAcceptAllButton().click();
        lexofficePage.extractPricingDetails(urlOfWebSite);

    }

    @When("^I extract getmyinvoices pricing details$")
    public void i_extract_getmyinvoices_pricing_details() {
   GetMyInvoicesPage getMyInvoicesPage =new GetMyInvoicesPage();
   getMyInvoicesPage.acceptAllCookies.click();
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", getMyInvoicesPage.preseMenu);
   getMyInvoicesPage.extractPricingDetails(urlOfWebSite);
    }

    @Then("^I should see the pricing details displayed correctly$")
    public void i_should_see_the_pricing_details_displayed_correctly() {

    }
}
