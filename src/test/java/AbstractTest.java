import engine.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.SearchPage;
import utilities.PropertyReader;

abstract class AbstractTest {


    private static WebDriver driver;
    protected HomePage homePage;
    protected SearchPage searchPage;


    @BeforeMethod
    public void init(){
        driver = DriverManager.getInstance().getDriver();

        this.homePage = PageFactory.initElements(driver ,HomePage.class);
        this.searchPage = PageFactory.initElements(driver, SearchPage.class);

        driver.get(PropertyReader.getGlobalProperty("appUrl"));

    }


    @AfterMethod()
    public void stop(){
        DriverManager.getInstance().closeDriver();
    }

}
