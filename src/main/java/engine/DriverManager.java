package engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.testng.Assert;
import utilities.PropertyReader;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static consts.PropertiesNames.PATH_TO_CHROME_BINARY;
import static consts.PropertiesNames.PATH_TO_FIREFOX_BINARY;
import static org.slf4j.LoggerFactory.getLogger;

public class DriverManager {

    private static final Logger LOG = getLogger(DriverManager.class);
    private static final Integer DELAY = 1;
    private static DriverManager instance = null;
    private static WebDriver driver = null;

    private DriverManager(){}

    public static synchronized DriverManager getInstance(){
        if (instance == null){
            instance = new DriverManager();
        }
       return instance;
    }
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
                    Assert.assertTrue(false, "Browser not recognised! " + PropertyReader.getBrowserName());
            }
        }
        return driver;
    }

    public static WebDriver getChromeDriver() {
        setPathsToDrivers();
        String path = PropertyReader.getGlobalProperty(PATH_TO_CHROME_BINARY);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(path);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        LOG.info("Open Chrome browser");
        return webDriver;
    }

    public static WebDriver getFirefoxDriver(){
        setPathsToDrivers();
        String path = PropertyReader.getGlobalProperty(PATH_TO_FIREFOX_BINARY);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(path);
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
        LOG.info("Open Firefox browser");
        return driver;
    }

    private static void setPathsToDrivers() {
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String sep = File.separator;
        String pathToDriver = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep;
        String chrome = "";
        String firefox = "";
        if (os.contains("win")) {
            chrome = pathToDriver + "chromedriver.exe";
            firefox = pathToDriver + "geckodriver.exe";
        } else if (os.contains("linux")) {
            chrome = pathToDriver + "chromedriver";
            firefox = pathToDriver + "geckodriver";
        } else if (os.contains("mac os x")){
            chrome = pathToDriver + "chromedriver";
            firefox = pathToDriver + "geckodriver";
        }
        else {
            Assert.assertTrue(false, "OS unrecognised, unable to open chrome browser.");
        }
        String ie = pathToDriver + "IEDriverServer.exe";
        String phantomJS = pathToDriver + "phantomjs.exe";
        System.setProperty("webdriver.gecko.driver", firefox);
        System.setProperty("webdriver.chrome.driver", chrome);
        System.setProperty("webdriver.ie.driver", ie);
        LOG.info("Path to firefox driver" + firefox);
        LOG.info("Path to chrome driver " + chrome);
        LOG.info("Path to IE driver " + ie);
        LOG.info("Path to PhantomJS driver " + phantomJS);
    }

    public static void closeDriver() {
        try {
            //getDriver().close();
            /**
             * Due to geckodriver issue https://github.com/mozilla/geckodriver/issues/966 commenting close() command
             */
            getDriver().quit();
            driver = null;
            LOG.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOG.info("cannot close browser: unreachable browser");
        }
    }



}
