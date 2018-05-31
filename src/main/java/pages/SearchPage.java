package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends AbstractPage {

    @FindBy(css = "a#pnnext")
    public WebElement nextResultsPageButton;

    @FindBy(css = "h3.r > a")
    public List<WebElement> results;

    public List<String> getPageTitles(){
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

    public void verifySearchResultsContainEnteredText(String searchedString){
        assertThat(getPageTitles().toString()).containsIgnoringCase(searchedString);
    }

}
