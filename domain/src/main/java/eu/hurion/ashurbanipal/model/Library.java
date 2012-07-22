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

    private final Set<Series> allSeries = new HashSet<Series>();

    private final Collection<AddBookListener> addBookListeners = new ArrayList<AddBookListener>();

    public boolean empty() {
        return books.isEmpty();
    }

    public void addBook(@Nonnull final Book book) {
        checkNotNull(book, "Null is not allowed in the library");
        if (books.contains(book)){
            return;
        }
        books.add(book);
        for (final AddBookListener addBookListener : addBookListeners) {
            addBookListener.onBookAdded(book);
        }
        LOG.debug("added book " + book);
    }

    /**
     * Add a Series that the owner of the library follow.
     * IF the Series is allready followed, it is not added a second time.
     *
     * @param series te Series that is followed.
     */
    public void addFollowedSeries(@Nonnull final Series series) {
        checkNotNull(series, "Null is not allowed in the library");
        if (allSeries.contains(series)){
            return;
        }
        allSeries.add(series);
        LOG.debug("added series " + series);
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

    public void addListener(final AddBookListener listener){
        addBookListeners.add(listener);
    }
}
