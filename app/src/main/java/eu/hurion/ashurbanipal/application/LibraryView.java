package eu.hurion.ashurbanipal.application;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import eu.hurion.ashurbanipal.model.AddBookListener;
import eu.hurion.ashurbanipal.model.Book;
import eu.hurion.ashurbanipal.model.Library;

import java.util.Collection;

/**
 * View the content of the library
 *
 * @author Nicolas Hurion
 */
public class LibraryView extends VerticalLayout implements AddBookListener {

    public static final String LIBRARY_TABLE = "library-table";
    public static final String TITLE_INPUT = "title-input";
    public static final String ADD_BOOK = "add-book";
    public static final String SERIES_INPUT = "series-input";
    public static final String MORE_SERIES = "more-series";
    private BeanContainer<String, Book> libraryContainer;

    private final LibraryService libraryService;

    public LibraryView(final Library library) {
        libraryService = new LibraryService(library);
        setSizeFull();
        setSpacing(true);
        setMargin(true);
        final Table libraryTable = bookTable(library.getBooks());
        libraryTable.setDebugId(LIBRARY_TABLE);
        addComponent(libraryTable);
        libraryTable.setSizeFull();
        final Form addBookForm = addBookForm();
        addComponent(addBookForm);
        library.addListener(this);
        final FollowedSeries followedSeries = new FollowedSeries(library);
        addComponent(followedSeries);
    }

    private Form addBookForm() {
        final Form bookForm = new Form();
        bookForm.setCaption("Add a new book");
        final TextField title = new TextField("Title");
        title.setDebugId(TITLE_INPUT);
        title.setNullRepresentation("");
        title.setRequired(true);
        bookForm.addField("title", title);

        final TextField series = new TextField("Series");
        series.setDebugId(SERIES_INPUT);
        series.setNullRepresentation("");
        series.setNullSettingAllowed(true);
        series.setValue(null);
        bookForm.addField("series", series);

        final CheckBox moreInSameSeries = new CheckBox("More on same series");
        moreInSameSeries.setDebugId(MORE_SERIES);
        bookForm.addField("more", moreInSameSeries);


        final HorizontalLayout okBar = new HorizontalLayout();
        okBar.setHeight("25px");
        bookForm.getFooter().addComponent(okBar);

        final Button okButton = new Button("Add");
        okButton.setDebugId(ADD_BOOK);
        okButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                final Object enteredTitle = title.getValue();
                if (enteredTitle == null) {
                    return;
                }
                final Object enteredSeries = series.getValue();
                libraryService.addBook(enteredTitle.toString() , enteredSeries == null? null :enteredSeries.toString());
                title.setValue(null);
                final Boolean value = (Boolean) moreInSameSeries.getValue();
                if (!value){
                    series.setValue(null);
                }
            }
        });
        okBar.addComponent(okButton);
        okBar.setComponentAlignment(okButton, Alignment.TOP_RIGHT);
        return bookForm;
    }

    private Table bookTable(final Collection<Book> books) {
        final Table bookTable = new Table();
        libraryContainer = new BeanContainer<String, Book>(Book.class);
        libraryContainer.setBeanIdProperty("title");
        libraryContainer.addAll(books);
        bookTable.setContainerDataSource(libraryContainer);
        bookTable.setVisibleColumns(new Object[]{"title", "series"});
        return bookTable;
    }

    @Override
    public void onBookAdded(final Book book) {
        libraryContainer.addBean(book);
    }
}
