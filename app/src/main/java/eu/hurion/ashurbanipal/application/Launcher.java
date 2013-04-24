package eu.hurion.ashurbanipal.application;

import eu.hurion.vaadin.heroku.VaadinForHeroku;

public class Launcher {
    public static void main(final String[] args) {
        VaadinForHeroku.herokuServer(VaadinForHeroku.forApplication(AshurbanipalApplication.class)).start();
    }

}
