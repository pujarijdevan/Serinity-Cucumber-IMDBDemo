package com.example.steps;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StepMovieListPage extends PageObject {

    @FindBy(xpath = "//button[@id='list-view-option-detailed']")
    WebElementFacade detailedViewButton;

    @FindBy(xpath = "//*[@id='__next']/main/div/div[2]/section/div/div[2]/div/ul")
    WebElementFacade moviesList;

    List<String> movieList = new ArrayList<String>();

    int totalMovieCount = 0;

    public List<WebElementFacade> findAllRatingElements() {
        return findAll(By.xpath("//span[contains(@aria-label, 'IMDb rating: ')]"));
    }

    public WebElementFacade getMovieRating(int index) {
        String xpath = "//*[@id='__next']/main/div/div[4]/section/div/div[2]/div/ul/li[" + index + "]/div[2]/div/div/span/div/span[@aria-label]";
        System.out.println("Xpath for rating: " + xpath);
        WebElementFacade element = find(By.xpath(xpath));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // 10-second wait
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElementFacade getMovieTitle(int index) {
        String xpath =  "//*[@id='__next']/main/div/div[4]/section/div/div[2]/div/ul/li[" + index + "]/div[2]/div/div/div[1]/a/h3[@class='ipc-title__text']";
        System.out.println("Xpath for title: " + xpath);
        WebElementFacade element = find(By.xpath(xpath));
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10)); // 10-second wait
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }
    public void waitForPageToLoad() {
        System.out.println("Waiting for the page to load...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20)); // 20-second wait
        wait.until(ExpectedConditions.visibilityOf(moviesList));
    }


    @Step("Click on Detailed View Button")
    public void clickOnDetailedViewButton() {
        System.out.println("Clicking on Detailed View Button");
        detailedViewButton.click();
        waitForPageToLoad();
    }

    //collect movie whoes rating is greater than 9.0
    @Step("Collect movies with rating greater than {0}")
    public void collectMovieRating(double rating) {
        int index = 1;
        while (index <= 250) {
            try {
                WebElementFacade ratingElement = getMovieRating(index);
                if (ratingElement != null && ratingElement.isVisible()) {
                    System.out.println("Rating Element found: " + ratingElement);
                    String ratingValue = ratingElement.getAttribute("aria-label");
                    System.out.println("Rating Value: " + ratingValue);
                    if (ratingValue != null && !ratingValue.isEmpty()) {
                        // Extract the numeric rating from the aria-label, assuming the format "IMDb rating: 9.3"
                        String[] parts = ratingValue.split(" ");
                        if (parts.length == 3) {
                            try {
                                double ratingDouble = Double.parseDouble(parts[2]);
                                if (ratingDouble >= rating) {
                                    WebElementFacade titleElement = getMovieTitle(index);
                                    if (titleElement != null && titleElement.isVisible()) {
                                        System.out.println("Movie Title: " + titleElement.getText() + " Rating: " + ratingValue);
                                        movieList.add(titleElement.getText());
                                    }
                                }
                            } catch (NumberFormatException e) {
                                // Handle the case where the rating is not a valid number
                                System.out.println("Invalid rating format: " + ratingValue);
                            }
                        }
                    }
                } else {
                    System.out.println("Rating element not visible for index: " + index);
                }
            } catch (Exception e) {
                System.out.println("Exception for index: " + index + " - " + e.getMessage());
            }
            index++;
        }
    }

    public void validateMovieListSize() {
        System.out.println("Movie List Size: " + movieList.size());
        Assert.assertEquals("Movie List Size", 7, movieList.size());
    }

    public void validateMovieInList(String movieName) {
    System.out.println("Validating Movie Name: " + movieList);
    for(String movie: movieList) {
        System.out.println("Movie Name: " + movie);
        if (movie.contains(movieName)) {
            System.out.println("Movie Name: " + movieName + " found in the list");
            Assert.assertTrue("Movie Name: " + movieName + " found in the list", true);
            break;
        }
    }
    }
}