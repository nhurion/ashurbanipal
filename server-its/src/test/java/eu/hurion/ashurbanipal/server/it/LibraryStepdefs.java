package eu.hurion.ashurbanipal.server.it;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Nicolas Hurion
 */
public class LibraryStepdefs {

    private final WebDriver driver;
    private final String baseUrl = "http://localhost:8080/";
    private String titleAdded;
    private String seriesAdded;

    private final LibraryPage libraryPage;

    public LibraryStepdefs(final SharedDriver webDriver) {
        driver = webDriver;
        libraryPage = PageFactory.initElements(webDriver, LibraryPage.class);
    }

    @Given("^I have an empty library$")
    public void iHaveALibrary() {
        driver.get(baseUrl + "/?restartApplication");
    }

    @Given("^A library with these titles:$")
    public void A_library_with_these_titles(final List<BookEntry> entries) throws Throwable {
        iHaveALibrary();
        for (final BookEntry entry : entries) {
            I_add_a_book_with_title_in_series(entry.title, entry.series);
        }
    }

    public class BookEntry {
        public String title;
        public String series;
    }

    @When("^I add a book with title \"([^\"]*)\"$")
    public void whenIAddABookWithTitle(final String title) {
        titleAdded = title;
        libraryPage.addBook(title);
    }

    @Then("^The book should be in the library$")
    public void theBookShouldBeInTheLibrary() {
        libraryPage.assertLibraryContainTitle(titleAdded);
    }

    @When("^I add a book with title \"([^\"]*)\" in series \"([^\"]*)\"$")
    public void I_add_a_book_with_title_in_series(final String title, final String series) throws Throwable {
        titleAdded = title;
        seriesAdded = series;
        libraryPage.addBook(title, series);
    }

    @Then("^The series should be in the followed series$")
    public void The_series_should_be_in_the_followed_series() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

}
