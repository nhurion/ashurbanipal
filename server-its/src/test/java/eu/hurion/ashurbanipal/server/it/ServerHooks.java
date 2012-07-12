package eu.hurion.ashurbanipal.server.it;

import com.bsb.common.vaadin.embed.EmbedVaadinServer;
import eu.hurion.ashurbanipal.server.Launcher;

/**
 * @author Nicolas Hurion
 */
public class ServerHooks {
    private EmbedVaadinServer application;
    private boolean applicationStarted;

   // @Before
    public void startup(){
        if (!applicationStarted){
            Launcher.setDevMode(true);
            application = Launcher.buildServer().wait(false).build();
            application.start();
            applicationStarted = true;
        }
    }

 //   @After
    public void shutdown() {
        application.stop();
    }
}
