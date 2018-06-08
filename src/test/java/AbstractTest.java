import engine.DriverManager;
import engine.DriverManager1;
import engine.DriverManagerFactory;
import engine.DriverTypes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.SearchPage;

abstract class AbstractTest {

    private DriverManager1 driverManager1;
    private static WebDriver driver;
    protected HomePage homePage;
    protected SearchPage searchPage;

    @BeforeMethod
    public void init(){
        //driverManager1 = DriverManagerFactory.getManager(DriverTypes.CHROME);
        driver = DriverManager.getDriver();


        this.homePage = PageFactory.initElements(driver ,HomePage.class);
        this.searchPage = PageFactory.initElements(driver, SearchPage.class);

        //driver.get("https://www.google.com/");

    }


    @AfterMethod()
    public void stop(){
        driver.quit();
    }

}
