package eu.hurion.ashurbanipal.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.bson.types.ObjectId;

import java.io.Serializable;

/**
 * @author Nicolas Hurion
 */
@Entity
public class Book implements Serializable, Comparable<Book> {

    @Id
    private String title;
    private Series series;

    //Constructor needed by Morphia
    private Book() {
    }

    public Book(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(final Series series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        return Objects.equal(getTitle(), ((Book) o).getTitle());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    @Override
    public int compareTo(final Book book) {
        return ComparisonChain.start()
                .compare(this.title, book.getTitle())
                .result();

    }

    @Override
    public String toString() {
        Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this)
                .add("title", getTitle());
        if (series != null) {
            toStringHelper = toStringHelper.add("series", getSeries());
        }
        return toStringHelper
                .toString();
    }
}
