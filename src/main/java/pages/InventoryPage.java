package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    private By inventoryContainer = By.id("inventory_container");
    private By selectFilter = By.className("select_container");
    private By selectFilterActiveOption = By.className("active_option");
    private By selectFilterOptions = By.className("product_sort_container");
    private By inventoryList = By.className("inventory_container");
    private By inventoryItem = By.className("inventory_item");
    private By inventoryItemName = By.className("inventory_item_name");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }

    private boolean isSelectFilterDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement filter = wait.until(ExpectedConditions.visibilityOfElementLocated(selectFilter));
            return filter.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Select Filter is not displayed on the page.");
            return false;
        }
    }

    public String findSelectFilterActiveName() {
        if (isSelectFilterDisplayed()) {
            WebElement activeOption = driver.findElement(selectFilterActiveOption);
            return activeOption.getText();
        } else {
            System.out.println("Select Filter is not displayed.");
            return null;
        }
    }

    public List<String> getInventoryItemNames() {
        List<WebElement> itemElements = driver.findElements(inventoryItemName);
        List<String> itemNames = new ArrayList<>();

        for (WebElement item : itemElements) {
            itemNames.add(item.getText());
        }
        return itemNames;
    }

    public void selectFilter(String filterName) {
        if (isSelectFilterDisplayed()) {
            WebElement filterOptions = driver.findElement(selectFilterOptions);
            Select filterOption = new Select(filterOptions);
            filterOption.selectByVisibleText(filterName);
        } else {
            System.out.println("Select Filter is not displayed.");
        }
    }

}
