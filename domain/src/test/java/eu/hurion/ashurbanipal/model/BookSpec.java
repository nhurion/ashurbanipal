package eu.hurion.ashurbanipal.model;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import jdave.Specification;
import jdave.contract.EqualsComparableContract;
import jdave.contract.EqualsHashCodeContract;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

import java.net.UnknownHostException;

/**
 * @author Nicolas Hurion
 */
@RunWith(JDaveRunner.class)
public class BookSpec extends Specification<Book>{
    public class ABook {
        private Book book;

        public Book create(){
            book = new Book("Necronomicon");
            return book;
        }

        public void isComparable(){
            specify(book, satisfies(new EqualsComparableContract<Book>() {
                @Override
                protected Book preceding() {
                    return new Book("A Game of Thrones");
                }

                @Override
                protected Book subsequent() {
                    return new Book("Neuromancer");
                }

                @Override
                protected Book equivalentByComparisonButNotByEqual() {
                    //Is there a book that should be equivalent but not equals?
                    return null;
                }
            }));
        }

        public void implementsEqualsCorrectly(){
            specify(book, satisfies(new EqualsHashCodeContract<Book>() {
                @Override
                protected Book equal() {
                    return new Book("Necronomicon");
                }

                @Override
                protected Book nonEqual() {
                    return new Book("Dune");
                }

                @Override
                protected Book subType() {
                    return null;
                }
            }));
        }

        public void canBeSaved() throws UnknownHostException {
            Mongo mongo = new Mongo("localhost", 27017);
            mongo.dropDatabase("testDB");
            Morphia morphia = new Morphia();
            morphia.map(Book.class);
            Datastore ds = morphia.createDatastore(mongo,"testDB");
            Key<Book> save = ds.save(book);
            Query<Book> books = ds.find(Book.class);
            Book book1 = books.get();
            specify(book1, book);
        }
    }
}
