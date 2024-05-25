package com.example.definitions;

import com.example.steps.StepHomePage;
import com.example.steps.StepLoginPage;
import com.example.steps.StepMovieListPage;
import com.example.steps.StepRegistrationPage;
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
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class MoviePageDefinition {

    @Managed
    WebDriver driver;

    @Steps
    StepHomePage homePage;

    @Steps
    StepRegistrationPage registrationPage;

    @Steps
    StepMovieListPage movieListPage;

    @Then("the user collects all movies with a {double}+ rating into an ArrayList")
    public void theUserCollectsAllMoviesWithARatingIntoAnArrayList(double rating) {
        //movieListPage.clickOnDetailedViewButton();
        System.out.println("Rating: " + rating);
        movieListPage.collectMovieRating(rating);
    }

    @When("the user writes an assertion to validate the number of movies in the ArrayList based on the number of results returned")
    public void theUserWritesAnAssertionToValidateTheNumberOfMoviesInTheArrayListBasedOnTheNumberOfResultsReturned() {
        movieListPage.validateMovieListSize();
    }

    @Then("the user validates that the movie {string} is listed in the ArrayList")
    public void theUserValidatesThatTheMovieIsListedInTheArrayList(String movieName) {
        movieListPage.validateMovieInList(movieName);
    }
}
