package eu.hurion.ashurbanipal.server.it;

import eu.hurion.ashurbanipal.application.LibraryView;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;

public class LibraryPage {

    private final WebDriver driver;

    @FindBy(id = LibraryView.TITLE_INPUT)
    private WebElement titleInput;
    @FindBy(id = LibraryView.SERIES_INPUT)
    private WebElement seriesInput;
    @FindBy(id = LibraryView.ADD_BOOK)
    private WebElement addButton;
    @FindBy(id = LibraryView.LIBRARY_TABLE)
    private WebElement libraryTable;

    public LibraryPage(final WebDriver driver) {
        this.driver = driver;
    }


   public void addBook(final String title){
       titleInput.clear();
       titleInput.sendKeys(title);
       addButton.click();
   }

    public void addBook(final String title, final String series){
        titleInput.clear();
        titleInput.sendKeys(title);
        seriesInput.clear();
        seriesInput.sendKeys(series);
        addButton.click();
    }

    public void assertLibraryContainTitle(final String title){
        assertTrue(libraryTable.getText().contains(title));
    }
}
