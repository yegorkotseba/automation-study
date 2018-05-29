import org.testng.annotations.Test;

public class GoogleSearchPageTest extends AbstractTest{

    @Test
    public void testGoogleSearchResult(){
        homePage.searchFor("selenium");

    }
}
