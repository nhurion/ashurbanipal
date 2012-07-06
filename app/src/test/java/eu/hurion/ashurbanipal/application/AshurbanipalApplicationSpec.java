package eu.hurion.ashurbanipal.application;

import jdave.Specification;
import jdave.contract.SerializableContract;
import jdave.junit4.JDaveRunner;
import org.junit.runner.RunWith;

/**
 * @author Nicolas Hurion
 */
@RunWith(JDaveRunner.class)
public class AshurbanipalApplicationSpec extends Specification<AshurbanipalApplication> {
    public class initializedApplication{
        private AshurbanipalApplication application;

        public AshurbanipalApplication create(){
            this.application = new AshurbanipalApplication();
            application.init();
            return application;
        }

        public void isSerializeable(){
            specify(application, satisfies(new SerializableContract()));
        }

    }
}
