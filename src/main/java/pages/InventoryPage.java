package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;

    private By inventoryContainer = By.id("inventory_container");
    private By selectFilter = By.className("select_container");
    private By selectFilterActiveOption = By.className("active_option");
    private By selectFilterOptions = By.className("product_sort_container");
    private By inventoryList = By.className("inventory_container");
    private By inventoryItem = By.className("inventory_item");
    private By inventoryItemName = By.className("inventory_item_name");
    private By inventoryItemDescription = By.className("inventory_item_desc");
    private By inventoryItemImage = By.cssSelector("img[class='inventory_item_img']");
    private By inventoryItemPrice = By.className("inventory_item_price");


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
            if (!item.getText().isEmpty()) {
                itemNames.add(item.getText());
            }
        }
        return itemNames;
    }

    public Integer getInventoryItemNamesCount() {
        return getInventoryItemNames().size();
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

    public List<Double> getInventoryItemPrices() {
        List<WebElement> itemElements = driver.findElements(inventoryItemPrice);
        List<String> itemPrices = new ArrayList<>();

        for (WebElement item : itemElements) {
            itemPrices.add(item.getText());
        }

        return itemPrices.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .collect(Collectors.toList());
    }

    public Integer getItemsCount() {
        return driver.findElements(inventoryItem).size();
    }

    public List<String> inventoryItemNamesNotStartWithDefinedString(String str) {
        return getInventoryItemNames().stream()
                .filter(name -> !name.startsWith(str))
                .collect(Collectors.toList());
    }

    public List<String> getInventoryItemDescriptions() {
        List<WebElement> itemElements = driver.findElements(inventoryItemDescription);
        List<String> itemDescriptions = new ArrayList<>();

        for (WebElement item : itemElements) {
            if (!item.getText().isEmpty()) {
                itemDescriptions.add(item.getText());
            }
        }
        return itemDescriptions;
    }

    public Integer getInventoryItemDescriptionsCount() {
        return getInventoryItemDescriptions().size();
    }

    public Integer getInventoryItemImagesCount() {
        return driver.findElements(inventoryItemImage).size();
    }

    public boolean imageUrlsRespondSuccessfully() {
        List<WebElement> images = driver.findElements(inventoryItemImage);
        boolean allValid = true;

        for (WebElement image : images) {
            String url = image.getAttribute("src");

            if (url == null || url.isEmpty()) {
                System.out.println("Image url is missing.");
                allValid = false;
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int statusCode = connection.getResponseCode();

                if (statusCode != 200) {
                    System.out.println("Broken image found: " + url + " [HTTP " + statusCode + "]");
                    allValid = false;
                }

            } catch (IOException e) {
                System.out.println("Error checking image: " + url);
                e.printStackTrace();
                allValid = false;
            }
        }

        return allValid;

    }


}

