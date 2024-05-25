package com.example.definitions;

import com.example.steps.StepHomePage;
import com.example.steps.StepLoginPage;
import com.example.steps.StepRegistrationPage;
import com.example.utils.Utility;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.example.utils.Utility.generateRandomString;
import static java.lang.Thread.*;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class RegistrationPageDefinition {
    @Managed
    WebDriver driver;

    @Steps
    StepHomePage homePage;

    @Steps
    StepRegistrationPage registrationPage;

    @Given("I am on the account registration page")
    public void iAmOnTheAccountRegistrationPage() throws InterruptedException {
        homePage.open();
        homePage.clickOnCloseSignInOverlayButton();
        homePage.clickOnSignInButton();
    }


    @When("I attempt to register with an email address that is already in use")
    public void iAttemptToRegisterWithAnEmailAddressThatIsAlreadyInUse(DataTable dataTable) throws InterruptedException {
        registrationPage.clickOnCreateNewAccount();
        registrationPage.enterCustomerName(generateRandomString(10));
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        registrationPage.enterEmail(data.get(0).get("email"));
        registrationPage.enterPassword(data.get(0).get("password"));
        registrationPage.enterConfirmPassword(data.get(0).get("password_confirmation"));
        registrationPage.clickOnRegister();
    }


    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String errorMessage) {
        registrationPage.verifyErrorMessage(errorMessage);
    }


    @Given("the user is on the IMDb homepage at {string}")
    public void theUserIsOnTheIMDbHomepageAt(String arg0) throws InterruptedException {
        homePage.open();
        sleep(1000);
    }

    @When("the user clicks on the {string} to view the Menu panel")
    public void theUserClicksOnTheMenuToViewTheMenuPanel(String arg0) {
        homePage.clickOnMenu();
    }

    @And("the user selects the {string} sub-link")
    public void theUserSelectsTheTopMoviesSubLink(String arg0) {
        homePage.clickOnTop250Movies();
    }

    @Given("I am a new user on the IMDb homepage")
    public void iAmANewUserOnTheIMDbHomepage() {
        homePage.open();
        homePage.clickOnCloseSignInOverlayButton();
    }

    @When("I click the “Sign In” link and then click the “Create a New Account” link")
    public void iClickTheSignInLinkAndThenClickTheCreateANewAccountLink() {
        homePage.clickOnSignInButton();
        registrationPage.clickOnCreateNewAccount();
    }

    @Then("I should be directed to the account registration page")
    public void iShouldBeDirectedToTheAccountRegistrationPage() {
        registrationPage.verifyRegistrationPage();
    }

    @When("I on the account registration page")
    public void iOnTheAccountRegistrationPage() {
        System.out.println("on Account registration page");
    }

    @And("I fill in the registration form with all required details")
    public void iFillInTheRegistrationFormWithAllRequiredDetails(DataTable tables) {
        List<Map<String, String>> data = tables.asMaps(String.class, String.class);
        registrationPage.enterCustomerName(data.get(0).get("name")+generateRandomString(8));
        registrationPage.enterEmail(data.get(0).get("email")+ Utility.generateUniqueIdentifier()+"@gmail.com");
        registrationPage.enterPassword(data.get(0).get("password"));
        registrationPage.enterConfirmPassword(data.get(0).get("password_confirmation"));
        registrationPage.clickOnRegister();
    }

    @Then("I should be able to complete the registration process and land on the authorized member page")
    public void iShouldBeAbleToCompleteTheRegistrationProcessAndLandOnTheAuthorizedMemberPage() throws InterruptedException {
        System.out.println("Registration successful");
    }

}

