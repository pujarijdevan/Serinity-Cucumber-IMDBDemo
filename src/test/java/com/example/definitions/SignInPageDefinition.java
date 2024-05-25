package com.example.definitions;

import com.example.steps.StepHomePage;
import com.example.steps.StepRegistrationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class SignInPageDefinition {
    @Managed
    WebDriver driver;

    @Steps
    StepHomePage homePage;

    @Steps
    StepRegistrationPage registrationPage;

    @Given("I am logged into the member page")
    public void iAmLoggedIntoTheMemberPage() {
        homePage.open();
        homePage.clickOnCloseSignInOverlayButton();
        homePage.clickOnSignInButton();
        registrationPage.signInWithIMDb();
        registrationPage.enterEmail("jagadax2508@gmail.com");
        registrationPage.enterPassword("Anujag0308$");
        registrationPage.clickOnSignInButton();
    }

    @When("I log out from the application")
    public void iLogOutFromTheApplication() {
        homePage.clickOnUserAccount();
        homePage.clickOnSignOutButton();
    }

    @Then("I should be redirected to the login page and not be able to access the member page")
    public void iShouldBeRedirectedToTheLoginPageAndNotBeAbleToAccessTheMemberPage() {
        homePage.verifySignInButton();
    }

    @When("I am on the login page")
    public void iAmOnTheLoginPage() {
        homePage.verifySignInButton();
    }


    @And("I fill in my email and password correctly")
    public void iFillInMyEmailAndPasswordCorrectly() {
        homePage.clickOnSignInButton();
        registrationPage.signInWithIMDb();
        registrationPage.enterEmail("jagadax2508@gmail.com");
        registrationPage.enterPassword("Anujag0308$");
        registrationPage.clickOnSignInButton();
    }

    @Then("I should be able to login and access the member page again")
    public void iShouldBeAbleToLoginAndAccessTheMemberPageAgain() {
        homePage.verifyUserAccount();
    }
}
