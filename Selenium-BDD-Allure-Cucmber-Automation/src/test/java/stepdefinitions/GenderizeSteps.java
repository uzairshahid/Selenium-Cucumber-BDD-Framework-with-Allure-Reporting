package stepdefinitions;

import io.cucumber.java.en.*;
import pages.GenderizePage;
import utils.AllureLogger;

public class GenderizeSteps {
    GenderizePage genderizePage = new GenderizePage();

    @Given("I open the website")
    public void i_open_the_website() throws InterruptedException {
        genderizePage.openWebsitePage();
    }

    @When("I enter {string} into the search box")
    public void iEnterIntoTheSearchBox(String name) {
        genderizePage.enterName(name);
        AllureLogger.info("Entering name: " + name);

    }

    @Then("I click the search button")
    public void iClickTheSearchButton() throws InterruptedException {
        genderizePage.clickSearchButton();

    }

    @Then("I should see results related to Entered Name")
    public void iShouldSeeResultsRelatedTo() {
        genderizePage.getMessage();
        AllureLogger.info("Verifying search results: "+genderizePage.getMessage());


    }
}
