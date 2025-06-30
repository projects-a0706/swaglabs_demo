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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;

    private By inventoryContainer = By.id("inventory_container");
    private By selectFilter = By.className("select_container");
    private By selectFilterActiveOption = By.cssSelector("span[data-test=\"active-option\"]");
    private By selectFilterOptions = By.cssSelector("select[data-test=\"product-sort-container\"]");
    private By inventoryItem = By.cssSelector("div[data-test=\"inventory-item\"]");
    private By inventoryItemName = By.cssSelector("div[data-test=\"inventory-item-name\"]");
    private By inventoryItemDescription = By.cssSelector("div[data-test=\"inventory-item-desc\"]");
    private By inventoryItemImage = By.cssSelector("img[class='inventory_item_img']");
    private By inventoryItemPrice = By.cssSelector("div[data-test=\"inventory-item-price\"]");
    private By addToCartButtonFirstItem = By.id("add-to-cart-sauce-labs-backpack");
    private By removeFromCartButtonFirstItem = By.id("remove-sauce-labs-backpack");
    private By cartBadge = By.cssSelector("span[data-test=\"shopping-cart-badge\"]");
    private By menuButton = By.id("react-burger-menu-btn");
    private By menuWrap = By.cssSelector(".bm-menu-wrap");
    private By itemMenuItem = By.cssSelector(".bm-item.menu-item");


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

    public Integer getInventoryItemPricesCount() {
        return driver.findElements(inventoryItemPrice).size();
    }

    public boolean isPriceFormatCorrect() {
        List<WebElement> priceElements = driver.findElements(inventoryItemPrice);
        Pattern pattern = Pattern.compile("^\\$\\d+\\.\\d{2}$");

        return priceElements.stream()
                .map(priceElement -> priceElement.getText().trim())
                .allMatch(text -> !text.isEmpty() && pattern.matcher(text).matches());
    }

    public void clickOnAddToCartButtonFirstItem() {
        driver.findElement(addToCartButtonFirstItem).click();
    }

    public void clickOnRemoveFromCartButtonFirstItem() {
        driver.findElement(removeFromCartButtonFirstItem).click();
    }

    public String getAddToCartButtonText() {
        return driver.findElement(addToCartButtonFirstItem).getText().trim();
    }

    public String getRemoveFromCartButtonText() {
       return driver.findElement(removeFromCartButtonFirstItem).getText().trim();
    }

    public String getCartBadge() {
        List<WebElement> badgeElements = driver.findElements(cartBadge);

        if (!badgeElements.isEmpty() && badgeElements.get(0).isDisplayed()) {
            return  badgeElements.get(0).getText().trim();
        } else {
            return "0";
        }
    }

    public void clickOnMenuButton() {
        driver.findElement(menuButton).click();
    }

    public boolean isMenuDisplayed() {
        return driver.findElement(menuWrap).isDisplayed();
    }

    public List<String> getMenuItemNames() {
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(itemMenuItem));

        return driver.findElements(itemMenuItem).stream().map(WebElement::getText).collect(Collectors.toList());
    }

}

