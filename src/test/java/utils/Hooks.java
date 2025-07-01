package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.InventoryPage;
import pages.LoginPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hooks {

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static InventoryPage inventoryPage;

    @Before
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox"); // Prevent Linux sandbox errors
        options.addArguments("--disable-dev-shm-usage"); // Avoid shared memory issues
        options.addArguments("--disable-gpu"); // Optional but safe
        options.addArguments("--remote-allow-origins=*"); // For Chrome 111+

        // Unique temporary user data dir
        Path tempProfileDir = Files.createTempDirectory("chrome-profile");
        options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath());

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @After
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
