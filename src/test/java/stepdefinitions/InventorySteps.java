package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static utils.Hooks.inventoryPage;
import static utils.Hooks.loginPage;

public class InventorySteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    private String username = "standard_user";
    private String password = "secret_sauce";

    @Given("the standard user is logged in and is on the inventory page")
    public void the_standard_user_is_logged_in_and_is_on_the_inventory_page(){
        logger.info("Enter username: {}.", username);
        loginPage.enterUsername(username);

        logger.info("Enter password: {}.", password);
        loginPage.enterPassword(password);

        logger.info("Click Login (success).");
        inventoryPage = loginPage.clickLoginButtonSuccess();
    }

    @Then("the filter should be preselected with alphabetical order {string}")
    public void the_filter_should_be_preselected_with_alphabetical_order(String nameFilter) {
        logger.info("Verify that filter name is {}.", nameFilter);
        String activeFilter = inventoryPage.findSelectFilterActiveName();
        Assertions.assertEquals(nameFilter, activeFilter, "The filter does not have expected name.");
    }

    @And("the items should be sorted in ascending alphabetical order")
    public void the_items_should_be_sorted_in_ascending_alphabetical_order() {
        logger.info("Get item names.");
        List<String> actualNames = inventoryPage.getInventoryItemNames();
        System.out.println(actualNames);

        logger.info("Sort item names in ascending alphabetical order.");
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);
        System.out.println(expectedNames);

        Assertions.assertEquals(actualNames, expectedNames,"Items are not sorted correctly.");
    }

    @And("the items should be sorted in descending alphabetical order")
    public void the_items_should_be_sorted_in_descending_alphabetical_order() {
        logger.info("Get item names.");
        List<String> actualNames = inventoryPage.getInventoryItemNames();
        System.out.println(actualNames);

        logger.info("Sort item names in descending alphabetical order.");
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        System.out.println(expectedNames);

        Assertions.assertEquals(actualNames, expectedNames,"Items are not sorted correctly.");
    }

    @When("the user selects {string} in the filter")
    public void the_user_selects_in_the_filter(String filterName) {
        logger.info("Select {} filter.", filterName);
        inventoryPage.selectFilter(filterName);
    }
}
