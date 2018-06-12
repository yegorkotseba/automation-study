package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends AbstractPage {

    @FindBy(css = "a#pnnext")
    private WebElement nextResultsPageButton;

    @FindBy(css = "h3.r > a")
    private List<WebElement> results;

    public WebElement getNextResultsPageButton() { return nextResultsPageButton; }

    public void setNextResultsPageButton(WebElement nextResultsPageButton) { this.nextResultsPageButton = nextResultsPageButton; }

    public List<WebElement> getResults() { return results; }

    public void setResults(List<WebElement> results) { this.results = results; }

    private List<String> getPageTitles(){
        List<String> titles = new ArrayList<String>();
        try {
            while (nextResultsPageButton.isDisplayed()) {
                for (WebElement element : results) {
                    titles.add(element.getText());
                }
                nextResultsPageButton.click();
            }
        }
        catch (NoSuchElementException e){
        }
            return titles;
    }

    /**
     * This is a debugging implementation of method getPageTitles()
     */
    /*private List<String> getPageTitles(){
        List<String> titles = new ArrayList<String>();
            for (WebElement element : results) {
                    titles.add(element.getText());
            }
            return titles;
        }*/

    public void verifySearchResultsContainEnteredText(String searchedString){
        assertThat(getPageTitles().toString()).containsIgnoringCase(searchedString);
    }

}
