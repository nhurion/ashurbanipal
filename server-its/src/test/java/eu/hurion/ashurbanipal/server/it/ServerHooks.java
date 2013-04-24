package eu.hurion.ashurbanipal.server.it;

import com.bsb.common.vaadin.embed.EmbedVaadinServer;
import eu.hurion.ashurbanipal.application.AshurbanipalApplication;

import static eu.hurion.vaadin.heroku.VaadinForHeroku.forApplication;
import static eu.hurion.vaadin.heroku.VaadinForHeroku.testServer;

/**
 * @author Nicolas Hurion
 */
public class ServerHooks {
    private EmbedVaadinServer application;
    private boolean applicationStarted;

   // @Before
    public void startup(){
        if (!applicationStarted){
            application = testServer(forApplication(AshurbanipalApplication.class)).build();
            application.start();
            applicationStarted = true;
        }
    }

 //   @After
    public void shutdown() throws InterruptedException {
        Thread.sleep(1000);
        application.stop();
    }
}
