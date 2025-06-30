package stepdefinitions;

import io.cucumber.datatable.DataTable;
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

import static utils.Hooks.*;

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

    @Then("the items should be sorted in ascending price order")
    public void the_items_should_be_sorted_in_ascending_price_order() {
        logger.info("Get item prices.");
        List<Double> actualPrices = inventoryPage.getInventoryItemPrices();
        System.out.println(actualPrices);

        logger.info("Sort item prices in ascending order.");
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        System.out.println(expectedPrices);

        Assertions.assertEquals(actualPrices, expectedPrices,"Items are not sorted correctly.");
    }

    @Then("the items should be sorted in descending price order")
    public void the_items_should_be_sorted_in_descending_price_order() {
        logger.info("Get item prices.");
        List<Double> actualPrices = inventoryPage.getInventoryItemPrices();
        System.out.println(actualPrices);

        logger.info("Sort item prices in descending order.");
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());
        System.out.println(expectedPrices);

        Assertions.assertEquals(actualPrices, expectedPrices,"Items are not sorted correctly.");
    }

    @Then("all items should have a title")
    public void all_items_should_have_a_title() {
        logger.info("Get items count and item titles count and verify the values are the same.");
        Assertions.assertEquals(inventoryPage.getItemsCount(),
                                inventoryPage.getInventoryItemNamesCount(),
                        "Items count " + inventoryPage.getItemsCount() + " does not match item names " + inventoryPage.getInventoryItemNamesCount() + " count.");
    }

    @And("every title starts with {string}")
    public void every_title_starts_with(String str) {
        logger.info("Verify all item names start with {}", str);
        Assertions.assertNull(inventoryPage.inventoryItemNamesNotStartWithDefinedString(str),
                    "The following name(s) does not match: " + inventoryPage.inventoryItemNamesNotStartWithDefinedString(str));
    }

    @Then("all items should have a description")
    public void all_items_should_have_a_description() {
        logger.info("Get items count and item descriptions count and verify the values are the same.");
        Assertions.assertEquals(inventoryPage.getItemsCount(),
                                inventoryPage.getInventoryItemDescriptionsCount(),
                        "Items count " + inventoryPage.getItemsCount() + " does not match item names " + inventoryPage.getInventoryItemNamesCount() + " count.");
    }

    @Then("all items should have an image")
    public void all_items_should_have_an_image() {
        logger.info("Get items count and item images count and verify the values are the same.");
        Assertions.assertEquals(inventoryPage.getItemsCount(),
                                inventoryPage.getInventoryItemImagesCount(),
                        "Items count " + inventoryPage.getItemsCount() + " does not match item images " + inventoryPage.getInventoryItemImagesCount() + " count.");
    }

    @And("the image is not broken")
    public void theImageIsNotBroken() {
        logger.info("Verifying images are not broken.");
        Assertions.assertTrue(inventoryPage.imageUrlsRespondSuccessfully(), "Image(s) is broken.");
    }

    @Then("all items should have a price")
    public void all_items_should_have_a_price() {
        logger.info("Get items count and item prices count and verify the values are the same.");
        Assertions.assertEquals(inventoryPage.getItemsCount(),
                                inventoryPage.getInventoryItemPricesCount(),
                        "Items count " + inventoryPage.getItemsCount() + " does not match item prices " + inventoryPage.getInventoryItemPricesCount() + " count.");
    }

    @And("the price should be in the US format")
    public void the_price_should_be_in_the_us_format() {
        logger.info("Verify price format.");
        Assertions.assertTrue(inventoryPage.isPriceFormatCorrect(), "Some item(s) has incorrect price format.");

    }

    @When("the user clicks on {string} button at the first item")
    public void the_user_clicks_on_add_remove_button_at_the_first_item(String buttonText) {
        logger.info("Click on {} button.", buttonText);

        switch (buttonText) {
            case "Add to cart":
                inventoryPage.clickOnAddToCartButtonFirstItem();
                break;
            case "Remove":
                inventoryPage.clickOnRemoveFromCartButtonFirstItem();
                break;
        }
    }

    @Then("the button text is changed to {string}")
    public void the_button_text_is_changed_to_remove(String buttonText) {
        logger.info("Verify button text is {}.", buttonText);

        switch (buttonText) {
            case "Add to cart":
                Assertions.assertEquals(buttonText, inventoryPage.getAddToCartButtonText(), "Unexpected text button.");
                break;
            case "Remove":
                Assertions.assertEquals(buttonText, inventoryPage.getRemoveFromCartButtonText(), "Unexpected text button.");
                break;
        }
    }

    @And("the cart icon in the top right updates to show {string} item")
    public void the_cart_icon_in_the_top_right_updates_to_show_n_item(String n) {
        logger.info("Verify the number of items ({}) displayed on the cart icon in the top right.", n);
        Assertions.assertEquals(n, inventoryPage.getCartBadge(), "Unexpected number of items in the cart.");
    }


    @When("the user clicks on the menu icon")
    public void the_user_clicks_on_the_menu_icon() {
        logger.info("Click on the menu");
        inventoryPage.clickOnMenuButton();
    }

    @Then("the menu should be opened")
    public void the_menu_should_be_opened() {
        logger.info("Verify that the menu is displayed");
        Assertions.assertTrue(inventoryPage.isMenuDisplayed(), "The menu is not displayed");
    }

    @And("the menu should contain the following items:")
    public void the_menu_should_contain_the_following_items(DataTable dataTable) {
        List<String> expectedItems = dataTable.asList();
        List<String> actualItems = inventoryPage.getMenuItemNames();

        Assertions.assertEquals(expectedItems, actualItems, "Unexpected menu item(s)");
    }
}
