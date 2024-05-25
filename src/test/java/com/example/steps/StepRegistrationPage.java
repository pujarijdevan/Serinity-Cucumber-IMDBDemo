package com.example.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;


public class StepRegistrationPage extends PageObject{


    @FindBy(linkText = "Create a New Account")
    WebElementFacade CreateNewAccount;

    @FindBy(id = "ap_customer_name")
    WebElementFacade CustomerName;

    @FindBy(id = "authportal-main-section")
    WebElementFacade AuthPortalMainSection;

    @FindBy(id = "ap_email")
    WebElementFacade Email;

    @FindBy(xpath = "//span[normalize-space()='Sign in with IMDb']")
    WebElementFacade SignInWithIMDb;

    @FindBy(id = "ap_password")
            WebElementFacade Password;

    @FindBy(id = "ap_password_check")
            WebElementFacade PasswordCheck;

    @FindBy(id = "continue")
            WebElementFacade Continue;

    @FindBy(xpath ="//span[@class='a-list-item' and contains(text(), \"You indicated you're a new customer, but an account already exists with the email address\")]")
    WebElementFacade ErrorMessage;

    @FindBy(xpath ="//input[@id='signInSubmit']")
            WebElementFacade LoginSignInSubmit;

    @Step("Signin with IMDb")
    public void signInWithIMDb() {
        SignInWithIMDb.click();
    }

    @Step("Click on Sign In")
    public void clickOnSignInButton() {
        LoginSignInSubmit.click();
    }

    @Step("Click on Create a New Account")
    public void clickOnCreateNewAccount() {
        CreateNewAccount.waitUntilClickable().click();
    }

    @Step("Enter Customer Name")
    public void enterCustomerName(String customerName) {
        CustomerName.sendKeys(customerName);
    }

    @Step("Enter Email")
    public void enterEmail(String email) {
        Email.sendKeys(email);
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        Password.sendKeys(password);
    }

    @Step("Re-enter Password")
    public void enterConfirmPassword(String passwordCheck) {
        PasswordCheck.sendKeys(passwordCheck);
    }

    @Step("Click on create your IMDB account")
    public void clickOnRegister() {
        Continue.click();
    }

    public void verifyErrorMessage(String errorMessage) {
        String actualErrorMessage = ErrorMessage.getText();
        assertTrue("The actual error message does not contain the expected text.", actualErrorMessage.contains(errorMessage));

    }
    public String getTitle() {
        System.out.println("Getting the title of the page"+getDriver().getTitle());
        return getDriver().getTitle();
    }

    public void verifyRegistrationPage() {
        Assert.assertTrue("The registration page is not displayed", getTitle().contains("Registration"));
    }
}
