package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement staticSearchButton;

    @FindBy(className = "sbdd_b")
    private WebElement searchDropdown;

    @FindBy(className = "lsb")
    private WebElement dynamicSearchButton; //button renders after searchDropdown appears

    public WebElement getSearchField() { return searchField; }

    public void setSearchField(WebElement searchField) { this.searchField = searchField; }

    public WebElement getSearchButton() { return staticSearchButton; }

    public void setSearchButton(WebElement searchButton) { this.staticSearchButton = staticSearchButton; }

    public WebElement getDynamicSearchButton() { return dynamicSearchButton; }

    public void setDynamicSearchButton(WebElement dynamicSearchButton) { this.dynamicSearchButton = dynamicSearchButton; }

    public void searchFor(String searchingText){
        searchField.click();
        searchField.sendKeys(searchingText);
        waitSecForElementIsClickable(dynamicSearchButton);
        dynamicSearchButton.click();
    }

}
