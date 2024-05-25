package com.example.steps;

import io.cucumber.java.pa.ਜਦੋਂ;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepHomePage extends PageObject{

  //  @FindBy(xpath = "//span[@class='ipc-btn__text' and text()='Sign In'])[1])")
 @FindBy(xpath = "//*[@id=\"imdbHeader\"]/div[2]/div[5]/a")
    WebElementFacade SignInButton;

 //@FindBy(id="mdbHeader-navDrawerOpen")
 @FindBy(xpath= "//*[@id='imdbHeader-navDrawerOpen']/span")
         WebElementFacade menuButton;

 @FindBy(css=".sc-fXayqf path:nth-child(2)")
 WebElementFacade closeSignInOverlay;

    @FindBy(xpath = "//span[normalize-space()='Top 250 Movies']")
    WebElementFacade Top250Movies;

    @FindBy(xpath ="//button[@title='Close']//*[name()='svg']")
    WebElementFacade closeSignInOverlayButton;

    @FindBy(xpath ="//*[@id='imdbHeader']/div[2]/div[5]/div/label[2]/span/span")
    WebElementFacade userAccount;

    @FindBy(xpath="//span[normalize-space()='Sign out']")
    WebElementFacade signOutButton;

    @Step("Click on Sign In Button")
    public void clickOnSignInButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement SignButton = wait.until(ExpectedConditions.elementToBeClickable(SignInButton));
        SignButton.click();
    }

    @Step("Click on Close SignIn Overlay Button")
    public void clickOnCloseSignInOverlayButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement closeOverlayButton = wait.until(ExpectedConditions.elementToBeClickable(closeSignInOverlayButton));
        closeOverlayButton.click();
    }

    @Step("Click on Menu")
    public void clickOnMenu() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement menuButtonElement = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButtonElement.click();
    }

    @Step("Click on Top 250 Movies")
    public void clickOnTop250Movies() {
        Top250Movies.click();
    }

    @Step("Close the SignIn Overlay")
    public void closeSignInOverlay() {
        closeSignInOverlay.click();
    }

    @Step("Click on Sign Out Button")
    public void clickOnSignOutButton() {
        signOutButton.waitUntilClickable().click();
    }

    @Step("Click on User Account")
    public void clickOnUserAccount() {
        userAccount.waitUntilClickable().click();
    }


    public void verifySignInButton() {
        SignInButton.isDisplayed();
    }

    public void verifyUserAccount() {
        userAccount.isDisplayed();
    }
}
