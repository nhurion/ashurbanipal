package eu.hurion.ashurbanipal.application;

import com.vaadin.Application;
import com.vaadin.ui.Window;
import eu.hurion.ashurbanipal.model.Library;

public class AshurbanipalApplication extends Application {

    @Override
    public void init() {
        final Window window = new Window();
        setMainWindow(window);
        final Library library = loadLibrary();
        window.setContent(new LibraryView(library));

    }

    private Library loadLibrary() {
        return new Library();
    }

}
