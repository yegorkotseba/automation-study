package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends AbstractPage {

    @FindBy(css = "a#pnnext")
    public WebElement nextButton;

    @FindBy(css = "h3.r > a")
    public List<WebElement> results;

    public List<String> getPageTitles(){
        List<String> titles = new ArrayList<String>();

        for (WebElement element : results){
            titles.add(element.getText());
        }
        return titles;
    }

    public void verifySearchResultsContainEnteredText(){
        //assertThat(app.searchPage.pageTitles().toString(), containsString("selenium"));
        assertThat(getPageTitles().toString()).containsIgnoringCase("selenium");
    }
}
