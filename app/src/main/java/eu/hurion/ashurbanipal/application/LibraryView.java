package eu.hurion.ashurbanipal.application;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.*;
import eu.hurion.ashurbanipal.model.AddBookListener;
import eu.hurion.ashurbanipal.model.Book;
import eu.hurion.ashurbanipal.model.Library;

import java.util.Collection;
import java.util.SortedSet;

/**
 * View the content of the library
 *
 * @author Nicolas Hurion
 */
public class LibraryView extends VerticalLayout implements AddBookListener {

    private BeanContainer<String, Book> libraryContainer;

    public LibraryView(final Library library) {
        setSizeFull();
        setSpacing(true);
        setMargin(true);
        final Table libraryTable = bookTable(library.getBooks());
        addComponent(libraryTable);
        libraryTable.setSizeFull();
        final Form addBookForm = addBookForm(library);
        addComponent(addBookForm);
        library.addListener(this);
    }

    private Form addBookForm(final Library library) {
        final Form bookForm = new Form();
        bookForm.setCaption("Add a new book");
        bookForm.addField("title", new TextField("Title"));

        final HorizontalLayout okBar = new HorizontalLayout();
        okBar.setHeight("25px");
        bookForm.getFooter().addComponent(okBar);

        Button okButton = new Button("OK");
        okButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                final Book newBook = new Book(bookForm.getField("title").getValue().toString());
                library.add(newBook);
            }
        });
        okBar.addComponent(okButton);
        okBar.setComponentAlignment(okButton, Alignment.TOP_RIGHT);
        return bookForm;
    }

    private Table bookTable(final Collection<Book> books){
        final Table bookTable = new Table();
        libraryContainer = new BeanContainer<String, Book>(Book.class);
        libraryContainer.setBeanIdProperty("title");
        libraryContainer.addAll(books);
        bookTable.setContainerDataSource(libraryContainer);
        libraryContainer.removeContainerProperty("series");
        return bookTable;
    }

    @Override
    public void onBookAdded(Book book) {
        libraryContainer.addBean(book);
    }
}
