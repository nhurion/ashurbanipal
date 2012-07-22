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
        final Library library = new Library();
//        library.addBook(new Book("Neuromancer"));
//        library.addBook(new Book("Necronomicon"));
//        Book firstHarryPotter = new Book("Harry Potter and the Philosopher Stone");
//        Series harryPotterSeries = new Series("Harry Potter", firstHarryPotter);
//        firstHarryPotter.setSeries(harryPotterSeries);
//        library.addBook(firstHarryPotter);
//        library.addBook(new Book("Dune"));
        return library;
    }

}
