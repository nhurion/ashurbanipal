package eu.hurion.ashurbanipal.model;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A Series represent a Series of books.
 * A series contains at least one book.
 *
 * @author Nicolas Hurion
 */
public class Series implements Serializable {
    private final Set<Book> books = new LinkedHashSet<Book>();
    private String title;

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

    @Override
    public String toString() {
        return Objects.toStringHelper(title).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Series)) {
            return false;
        }
        return Objects.equal(getTitle(), ((Series) o).getTitle());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
