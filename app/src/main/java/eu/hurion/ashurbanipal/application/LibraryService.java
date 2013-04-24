package eu.hurion.ashurbanipal.application;

import eu.hurion.ashurbanipal.model.Book;
import eu.hurion.ashurbanipal.model.Library;
import eu.hurion.ashurbanipal.model.Series;

import java.io.Serializable;
import java.util.Collection;

public class LibraryService implements Serializable {

    private final Library library;

    public LibraryService(final Library library) {
        this.library = library;
    }

    public Book addBook(final String title, final String seriesTitle) {
        final Collection<Book> exactBookByTitle = library.findExactBookByTitle(title);
        if (exactBookByTitle.isEmpty()){
             return createNewBook(title, seriesTitle);
        } else if (exactBookByTitle.size() == 1){
            return exactBookByTitle.iterator().next();
        } else {
            return handleMoreResult(exactBookByTitle);
        }
    }

    private Book handleMoreResult(final Collection<Book> exactBookByTitle) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Book createNewBook(final String title, final String seriesTitle) {
        final Book newBook = new Book(title);
        if (seriesTitle != null) {
            final Series newSeries = new Series(seriesTitle, newBook);
            newBook.setSeries(newSeries);
        }
        library.addBook(newBook);
        return newBook;
    }


}
