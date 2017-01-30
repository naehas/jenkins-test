package utils

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

import java.util.concurrent.TimeUnit

class DriverFactory {

    private static WebDriver driver;

    public DriverFactory() {
        initialize()
    }

    public static void initialize() {
        if (driver == null) createNewDriverInstance()
    }

    private static void createNewDriverInstance() {
        driver = new FirefoxDriver()
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        driver
    }

    public static void destroyDriver() {
        driver.quit()
        driver = null
    }
}
