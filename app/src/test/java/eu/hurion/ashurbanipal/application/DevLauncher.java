package eu.hurion.ashurbanipal.application;

import eu.hurion.vaadin.heroku.VaadinForHeroku;

public class DevLauncher {

    public static void main(final String[] args) {
        VaadinForHeroku.localServer(VaadinForHeroku.forApplication(AshurbanipalApplication.class)).start();
    }
}
