package eu.hurion.ashurbanipal.application;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import eu.hurion.ashurbanipal.model.AddSeriesListener;
import eu.hurion.ashurbanipal.model.Library;
import eu.hurion.ashurbanipal.model.Series;

import java.util.Collection;

@SuppressWarnings("serial")
public class FollowedSeries extends VerticalLayout implements AddSeriesListener {

    private final Library library;
    private static final String SERIES_TABLE = "followed-series-table";
    private BeanContainer<String, Series> libraryContainer;

    public FollowedSeries(final Library library) {
        this.library = library;
        final Table libraryTable = seriesTable(library.getFollowedSeries());
        libraryTable.setDebugId(SERIES_TABLE);
        addComponent(libraryTable);
        libraryTable.setSizeFull();
    }

    private Table seriesTable(final Collection<Series> series) {
        final Table bookTable = new Table();
        libraryContainer = new BeanContainer<String, Series>(Series.class);
        libraryContainer.setBeanIdProperty("title");
        libraryContainer.addAll(series);
        bookTable.setContainerDataSource(libraryContainer);
        bookTable.setVisibleColumns(new Object[]{"title"});
        return bookTable;
    }

    @Override
    public void onSeriesAdded(final Series series) {
        libraryContainer.addBean(series);
    }
}
