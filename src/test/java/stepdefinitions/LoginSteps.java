package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static utils.Hooks.loginPage;
import static utils.Hooks.inventoryPage;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {logger.info("User is on login page.");
    }

    @When("the user enters username {string} and password {string}")
    public void the_user_logs_in_with_credentials(String username, String password) {
        logger.info("Enter username: {}.", username);
        loginPage.enterUsername(username);

        logger.info("Enter password: {}.", password);
        loginPage.enterPassword(password);
    }

    @When("the user clicks on login button")
    public void the_user_clicks_on_login_button_success () {
        logger.info("Click Login (success).");
        inventoryPage = loginPage.clickLoginButtonSuccess();
    }

    @When("the user clicks on login button expecting an error")
    public void the_user_clicks_on_login_button_error () {
        logger.info("Click Login (error expected).");
        loginPage.clickLoginButtonError();
    }

    @Then("the user should be redirected to the inventory page")
    public void the_user_should_be_redirected_to_the_products_page() {
        logger.info("Verifying that user is redirected to the inventory page.");
        Assertions.assertTrue(inventoryPage.isAt(), "User was not redirected to the inventory page.");}

    @Then("the user should stay on the login page")
    public void the_user_should_stay_on_the_login_page() {
        logger.info("Verifying that user stays on the login page.");
        Assertions.assertTrue(loginPage.isAt(), "User is not on the login page.");}

    @And("the error message should be displayed {string}")
    public void theErrorMessageShouldBeDisplayed(String message) {
        logger.info("Verifying that error message {} is displayed.", message);
        Assertions.assertEquals(loginPage.getErrorMessage(), message, "Expected error message is not displayed.");}

    @When("x-icons should be displayed in the username and password fields")
    public void x_icons_should_be_displayed_in_the_username_and_password_fields() {
        logger.info("Verify x-icons are displayed");
        Assertions.assertTrue(loginPage.isXIconUsernameDisplayed(), "x-icon is not displayed in the username field.");
        Assertions.assertTrue(loginPage.isXIconPasswordDisplayed(), "x-icon is not displayed in the passsword field.");
    }

}
