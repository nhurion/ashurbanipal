package eu.hurion.ashurbanipal.model;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSortedSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Library contain books
 *
 * @author Nicolas Hurion
 */
public class Library implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Library.class);

    private final Set<Book> books = new HashSet<Book>();

    private final Collection<AddBookListener> addBookListeners = new ArrayList<AddBookListener>();

    public boolean empty() {
        return books.isEmpty();
    }

    public void add(@Nonnull final Book book) {
        checkNotNull(book, "Null is not allowed in the library");
        if (books.contains(book)){
            return;
        }
        books.add(book);
        for (AddBookListener addBookListener : addBookListeners) {
            addBookListener.onBookAdded(book);
        }
        LOG.debug("added book " + book);
    }

    public Collection<Book> getBooks() {
        return ImmutableSortedSet.copyOf(books);
    }

    public Collection<Book> findBookByTitle(final String title) {
        return Collections2.filter(books, new Predicate<Book>() {
            @Override
            public boolean apply(final Book book) {
                return book.getTitle().contains(title);
            }
        });
    }

    public void addListener(AddBookListener listener){
        addBookListeners.add(listener);
    }
}
