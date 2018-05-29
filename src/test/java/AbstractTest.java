import engine.DriverManager;
import engine.DriverManagerFactory;
import engine.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import pages.SearchPage;

abstract class AbstractTest {

    DriverManager driverManager;
    WebDriver driver;
    public HomePage homePage;
    public SearchPage searchPage;

    @BeforeClass
    public void init(){
        driverManager = DriverManagerFactory.getManager(DriverTypes.CHROME);
        driver = driverManager.getDriver();

        this.homePage = PageFactory.initElements(driver ,HomePage.class);
        this.searchPage = PageFactory.initElements(driver, SearchPage.class);

        driver.get("https://www.google.com/");

    }


    @AfterClass(alwaysRun = false)
    public void stop(){
        driverManager.quitDriver();
    }

}
