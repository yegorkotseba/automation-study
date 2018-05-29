package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {


    @FindBy(id = "lst-ib")
    public static WebElement searchField;

    @FindBy(name = "btnK")
    public static WebElement searchButton;

    public void searchFor(String searchingText){
        searchField.sendKeys(searchingText);
        searchButton.click();
    }

}
