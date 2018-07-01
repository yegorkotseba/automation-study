import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleSearchPageTest extends AbstractTest{

    @DataProvider (name = "searchData")
    public static Object[][] searchData() {

        return new Object[][] {{ "selenium"}, {"awesome"}, { "tool"}};
    }

    @Test (dataProvider = "searchData")
    public void testGoogleSearchResult(String searchData){
        homePage.searchFor(searchData);
        searchPage.verifySearchResultsContainEnteredText(searchData);
    }
}
