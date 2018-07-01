import engine.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.SearchPage;
import utilities.PropertyReader;

abstract class AbstractTest {


    private static WebDriver driver;
    protected HomePage homePage;
    protected SearchPage searchPage;


    @BeforeClass
    public void init(){
        driver = DriverManager.getDriver();

        this.homePage = PageFactory.initElements(driver ,HomePage.class);
        this.searchPage = PageFactory.initElements(driver, SearchPage.class);

    }

    @BeforeMethod
    public void goToStartPage(){
        driver.get(PropertyReader.getGlobalProperty("appUrl"));
    }


    @AfterClass()
    public void stop(){
        DriverManager.closeDriver();
    }

}
