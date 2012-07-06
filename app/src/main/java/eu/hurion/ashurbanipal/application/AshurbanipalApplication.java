package eu.hurion.ashurbanipal.application;

import com.vaadin.Application;
import com.vaadin.ui.*;
import eu.hurion.ashurbanipal.model.Book;
import eu.hurion.ashurbanipal.model.Library;
import eu.hurion.ashurbanipal.model.Series;

public class AshurbanipalApplication extends Application {

    public static final String HELLO_WORLD = "Hello from Heroku, ";
    public static final String BUTTON_CAPTION = "Click me";
    public static final String NAME_LABEL = "What is your name?";

    @Override
    public void init() {
        final Window window = new Window();
        setMainWindow(window);
//        window.setContent(buildContent());
        final Library library = loadLibrary();
        window.setContent(new LibraryView(library));

    }

    private Library loadLibrary() {
        Library library = new Library();
        library.add(new Book("Neuromancer"));
        library.add(new Book("Necronomicon"));
        Book firstHarryPotter = new Book("Harry Potter and the Philosopher Stone");
        Series harryPotterSeries = new Series("Harry Potter", firstHarryPotter);
        firstHarryPotter.setSeries(harryPotterSeries);
        library.add(firstHarryPotter);
        library.add(new Book("Dune"));
        return library;
    }

    private ComponentContainer buildContent() {
        final FormLayout formLayout = new FormLayout();
        formLayout.setSpacing(true);
        formLayout.setSizeUndefined();

        final TextField nameInput = new TextField();
        nameInput.setCaption(NAME_LABEL);
        formLayout.addComponent(nameInput);

        final Button showButton = new Button(BUTTON_CAPTION, new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                getMainWindow().showNotification(
                        HELLO_WORLD + nameInput.getValue() + " !",
                        ""
                );

            }
        });
        formLayout.addComponent(showButton);
        final VerticalLayout vl = new VerticalLayout();
        vl.addComponent(formLayout);
        vl.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        vl.setSizeFull();
        return vl;
    }
}
