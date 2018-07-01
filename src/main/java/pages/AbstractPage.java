package pages;


import engine.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import utilities.PropertyReader;

import static org.slf4j.LoggerFactory.getLogger;

abstract class AbstractPage {

    public static final int DELAY_MILLIS = 60 * 1000;
    public static final int DELAY_SEC = 60;
    public static final int DELAY_SMALL_MILLIS = 5 * 1000;
    private static final Logger LOG = getLogger(PropertyReader.class);
    private WebDriver driver = DriverManager.getDriver();

    public void waitSecForElementIsClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DELAY_SEC);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public void waitSecForElementIsVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DELAY_SEC);
            wait.until(ExpectedConditions.elementToBeSelected(element));
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    public void waitSecForElementNotVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, DELAY_SEC);
            wait.until(ExpectedConditions.invisibilityOf(element));
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
