import engine.DriverManager;
import engine.DriverManagerFactory;
import engine.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.SearchPage;

abstract class AbstractTest {

    DriverManager driverManager;
    WebDriver driver;
    public HomePage homePage;
    public SearchPage searchPage;

    @BeforeMethod
    public void init(){
        driverManager = DriverManagerFactory.getManager(DriverTypes.CHROME);
        driver = driverManager.getDriver();

        this.homePage = PageFactory.initElements(driver ,HomePage.class);
        this.searchPage = PageFactory.initElements(driver, SearchPage.class);

        driver.get("https://www.google.com/");

    }


    @AfterMethod()
    public void stop(){
        driverManager.quitDriver();
    }

}
