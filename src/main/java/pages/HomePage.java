package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    private WebDriver driver;


    @FindBy(id = "lst-ib")
    private static WebElement searchField;

    @FindBy(name = "btnK")
    private static WebElement searchButton;

    public void searchFor(String searchingText){
        searchField.sendKeys(searchingText);
        searchButton.click();
    }

}
