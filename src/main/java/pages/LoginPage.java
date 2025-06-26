package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private By usernameInputField = By.id("user-name");
    private By passwordInputField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginContainer = By.id("login_button_container");
    private By errorMessage = By.cssSelector("h3[data-test=\"error\"]");
    private By xIconUsername = By.cssSelector("#login_button_container > div > form > div:nth-child(1) > svg");
    private By xIconPassword = By.cssSelector("#login_button_container > div > form > div:nth-child(2) > svg");

    public LoginPage(WebDriver driver) {this.driver = driver; }

    public void enterUsername (String username) {
        driver.findElement(usernameInputField).sendKeys(username);
    }

    public void enterPassword (String password) {
        driver.findElement(passwordInputField).sendKeys(password);
    }

    // valid scenario
    public InventoryPage clickLoginButtonSuccess () {
        driver.findElement(loginButton).click();
        return new InventoryPage(driver);
    }

    // invalid scenario
    public void clickLoginButtonError () {
        driver.findElement(loginButton).click();
    }

    public boolean isAt () {
        return driver.findElement(loginContainer).isDisplayed();
    }

    public String getErrorMessage () {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isXIconUsernameDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement xIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(xIconUsername));
            return xIcon.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("x-icon is not displayed within the timeout.");
            return false;
        }
    }

    public boolean isXIconPasswordDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement xIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(xIconPassword));
            return xIcon.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("x-icon is not displayed within the timeout.");
            return false;
        }
    }

}
