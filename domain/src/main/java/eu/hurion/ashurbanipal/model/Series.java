package eu.hurion.ashurbanipal.model;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.io.Serializable;
import java.util.*;

import static com.google.common.collect.Ordering.natural;

/**
 * A Series represent a Series of books.
 * A series contains at least one book.
 *
 * @author Nicolas Hurion
 */
public class Series implements Serializable {
    private final Set<Book> books = new LinkedHashSet<Book>();
    private String title;
    private boolean complete;

    public Series(final String title, final Book firstBook, final Book... books) {
        this.title = title;
        this.books.add(firstBook);
        this.books.addAll(Arrays.asList(books));
    }

    public Set<Book> getBooks() {
        return ImmutableSet.copyOf(books);
    }

    public String getTitle() {
        return title;
    }

    public void add(final Book book) {
        books.add(book);
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(title).toString();
    }
}
