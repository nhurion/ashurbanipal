package eu.hurion.ashurbanipal.model;

import jdave.Block;
import jdave.Specification;
import jdave.contract.EqualsHashCodeContract;
import jdave.contract.SerializableContract;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Nicolas Hurion
 */
@RunWith(JDaveRunner.class)
public class SeriesSpec extends Specification<Series> {

    public static final Book THE_WINDS_OF_WINTER = new Book("The Winds of Winter");
    public static final Book A_GAME_OF_THRONES = new Book("A Game of Thrones");
    public static final Book A_CLASH_OF_KINGS = new Book("A Clash of Kings");
    public static final Book A_STORM_OF_SWORDS = new Book("A Storm of Swords");
    public static final Book A_FEAST_OF_CROWS = new Book("A Feast for Crows");
    public static final Book A_DANCE_WITH_DRAGONS = new Book("A Dance with Dragons");

    public class IncompleteSeries {
        private Series series;

        public Series create() {
            series = new Series("A Song of Ice and Fire",
                    A_GAME_OF_THRONES, A_CLASH_OF_KINGS,
                    A_STORM_OF_SWORDS, A_FEAST_OF_CROWS,
                    A_DANCE_WITH_DRAGONS);
            return series;
        }

        public void acceptNewBooks(){
            series.add(THE_WINDS_OF_WINTER);
            specify(series.getBooks(), must.contain(THE_WINDS_OF_WINTER));
        }

        public void doesNotAllowDirectModificationOfBookList(){
            specify(new Block() {
                public void run() throws Throwable {
                    series.getBooks().add(new Book("dummy"));
                }
            }, should.raise(RuntimeException.class));
        }

        public void hasBookInRightOrder(){
            specify(series.getBooks(),containsInOrder(A_GAME_OF_THRONES,A_CLASH_OF_KINGS,A_STORM_OF_SWORDS,A_FEAST_OF_CROWS,A_DANCE_WITH_DRAGONS));
        }


        public void isSerializeable(){
            specify(series, satisfies(new SerializableContract()));
        }

        public void implementsEqualsCorrectly(){
            specify(series, satisfies(new EqualsHashCodeContract<Series>() {
                @Override
                protected Series equal() {
                    return new Series("A Song of Ice and Fire", A_GAME_OF_THRONES);
                }

                @Override
                protected Series nonEqual() {
                    return new Series("Dune", new Book("Dune") );
                }

                @Override
                protected Series subType() {
                    return null;
                }
            }));
        }
    }
}
