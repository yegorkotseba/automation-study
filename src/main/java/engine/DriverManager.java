package engine;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static consts.PropertiesNames.PATH_TO_CHROME_BINARY;
import static org.testng.AssertJUnit.assertTrue;

public class DriverManager {

    private static WebDriver driver = null;
    private static final Integer DELAY = 1;

    public static synchronized WebDriver getDriver() {
        if (driver == null || driver.toString().contains("null")) {
            switch (PropertyReader.getBrowserName()) {
                /*case ("ie"):
                    driver = getInternetExplorerDriver();
                    break;*/
                case ("firefox"):
                    driver = getFirefoxDriver();
                    break;
                case ("chrome"):
                    driver = getChromeDriver();
                    break;
                default:
                    assertTrue("Browser not recognised! " + PropertyReader.getBrowserName(), false);
            }
        }
        return driver;
    }

    public static WebDriver getFirefoxDriver() {
        WebDriver webDriver = new FirefoxDriver();
        System.out.println("Open Firefox browser");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        return webDriver;
    }

    public static WebDriver getChromeDriver() {
        setPathsToDrivers();
        String path = PropertyReader.getGlobalProperty(PATH_TO_CHROME_BINARY);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(path);
        WebDriver webDriver = new ChromeDriver(options);
        //webDriver.manage().window().maximize();
        //webDriver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        System.out.println("Open Chrome browser");
        return webDriver;
    }

    private static void setPathsToDrivers() {
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String sep = File.separator;
        String pathToDriver = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep;
        String chrome = "";
        if (os.contains("win")) {
            chrome = pathToDriver + "chromedriver.exe";
        } else if (os.contains("linux")) {
            chrome = pathToDriver + "chromedriver";
        } else if (os.contains("mac os x")){
            chrome = pathToDriver + "chromedriver";
        }
        else {
            assertTrue("OS unrecognised, unable to open chrome browser.", false);
        }
        String ie = pathToDriver + "IEDriverServer.exe";
        String phantomJS = pathToDriver + "phantomjs.exe";
        System.setProperty("webdriver.chrome.driver", chrome);
        System.setProperty("webdriver.ie.driver", ie);
        System.out.println("Path to chrome driver " + chrome);
        System.out.println("Path to IE driver " + ie);
        System.out.println("Path to PhantomJS driver " + phantomJS);
    }

}
