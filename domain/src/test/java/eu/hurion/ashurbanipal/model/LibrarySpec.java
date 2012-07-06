package eu.hurion.ashurbanipal.model;

import jdave.Block;
import jdave.Specification;
import jdave.contract.SerializableContract;
import jdave.junit4.JDaveRunner;
import org.jmock.Expectations;
import org.junit.runner.RunWith;

/**
 * @author Nicolas Hurion
 */
@RunWith(JDaveRunner.class)
public class LibrarySpec extends Specification<Library> {

    public static final Book NECRONOMICON = new Book("Necronomicon");
    public static final Book HITCHHIKER = new Book("The Ultimate Hitchhiker guides to the galaxy");
    public static final Book DUNE = new Book("Dune");

    public class EmptyLibrary{
        private Library library;

        public Library create(){
            library = new Library();
            return library;
        }

        public void isEmpty(){
            specify(library, must.be.empty());
        }

        public void isNoLongerEmptyAfterAddingABook(){
            library.add(HITCHHIKER);
            specify(library, must.not().be.empty());
        }

        public void doesNotAcceptNullAsBook(){
            specify(new Block() {
                public void run() throws Throwable {
                    library.add(null);
                }
            }, should.raise(RuntimeException.class));
        }
    }

    public class LibraryWithBooks{
        private Library library;

        public Library create(){
            library = new Library();
            library.add(NECRONOMICON);
            library.add(HITCHHIKER);
            library.add(DUNE);
            return library;
        }

        public void isNotEmpty(){
            specify(library,must.not().be.empty());
        }

        public void containsAllTheBooks(){
            specify(library.getBooks(),containsInOrder(DUNE, NECRONOMICON, HITCHHIKER));
        }

        public void canFindABookBasedOnTitle(){
            specify(library.findBookByTitle("Necronomicon"), contains(NECRONOMICON));
        }

        public void isSerializable(){
            specify(library, satisfies(new SerializableContract()));
        }

        public void cannotAddTwiceSameBook(){
            final int sizeBefore = library.getBooks().size();
            library.add(NECRONOMICON);
            specify(library.getBooks().size(), sizeBefore);
        }

        public void cannotAddTwoBooksWithSameTitle(){
            final int sizeBefore = library.getBooks().size();
            library.add(new Book("Necronomicon") );
            specify(library.getBooks().size(), sizeBefore);
        }
    }

    public class LibraryWithListeners{
        private Library library;
        private AddBookListener addBookListener;

        public Library create(){
            library = new Library();
            addBookListener = mock(AddBookListener.class);
            library.addListener(addBookListener);
            return library;
        }

        public void onBookAddedCalledWhenAddingABook(){
            final Book actualBook = DUNE;
            checking(new Expectations() {{
                one(addBookListener).onBookAdded(actualBook);
            }});
            library.add(actualBook);
        }
    }
}
