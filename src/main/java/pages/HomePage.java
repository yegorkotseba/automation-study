package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    public WebElement getSearchField() { return searchField; }

    public void setSearchField(WebElement searchField) { this.searchField = searchField; }

    public WebElement getSearchButton() { return searchButton; }

    public void setSearchButton(WebElement searchButton) { this.searchButton = searchButton; }

    public void searchFor(String searchingText){
        searchField.sendKeys(searchingText);
        searchButton.click();
    }

}
