package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractEntityView<DTO extends AbstractDto, Service extends AbstractService> extends VerticalLayout implements View {

    private Service service;

    private HorizontalLayout mainInstrumentsLayout = new HorizontalLayout();

    Button addButton = new Button("Add");
    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");
    Button clearSearchField = new Button(VaadinIcons.CLOSE_CIRCLE);

    TextField searchField = new TextField();

    private DataProvider<DTO, String> dataProvider;
    private ConfigurableFilterDataProvider<DTO, Void, String> filteredDataProvider;
    private Grid<DTO> grid;

    @Autowired
    public AbstractEntityView(final Service service) {
        this.service = service;
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setupInstrumentsLayout();
        setupGrid();
        setupInstruments();
    }

    protected abstract Class<DTO> getEntityClass();

    protected abstract String getSearchFieldPlaceholder();

    private void setupInstrumentsLayout() {
        searchField.setPlaceholder(getSearchFieldPlaceholder());
        final CssLayout searchInstrumentsLayout = new CssLayout();
        searchInstrumentsLayout.addComponents(searchField, clearSearchField);
        mainInstrumentsLayout.addComponent(searchInstrumentsLayout);
        mainInstrumentsLayout.addComponent(addButton);
        mainInstrumentsLayout.addComponent(editButton);
        mainInstrumentsLayout.addComponent(deleteButton);
        setEditDeleteButtonsEnabled(false);
        addComponent(mainInstrumentsLayout);
    }

    private void setupInstruments() {
        addButton.addClickListener(click -> {
            final Window addWindow = new Window();
            addWindow.addCloseListener(close -> dataProvider.refreshAll());
            getUI().addWindow(addWindow);
        });
        editButton.addClickListener(click -> {
            final Window editWindow = new Window();
            editWindow.addCloseListener(close -> dataProvider.refreshAll());
            getUI().addWindow(editWindow);
        });
        deleteButton.addClickListener(click -> {
            service.deleteAll(grid.getSelectedItems());
            grid.getDataProvider().refreshAll();
        });
        clearSearchField.addClickListener(click -> {
            searchField.clear();
            filteredDataProvider.setFilter(null);
        });
        setupSearchField();
    }

    private void setupGrid(){
        grid = new Grid<>(getEntityClass());
        setupGridDataProvider();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selection -> {
            final int selectedLinesCount = selection.getAllSelectedItems().size();
            if (selectedLinesCount == 1){
                setEditDeleteButtonsEnabled(true);
            } else if (selectedLinesCount > 1){
                editButton.setEnabled(false);
                deleteButton.setEnabled(true);
            } else {
                setEditDeleteButtonsEnabled(false);
            }
        });
        addComponent(grid);
        setExpandRatio(grid, 30);
    }

    private void setupGridDataProvider(){
        dataProvider = DataProvider.fromFilteringCallbacks(
                query -> service.findByFilterQueryWithPagination(query),
                query -> Math.toIntExact(service.findByFilterQueryWithPagination(query).count())
        );
//        filteredDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(dataProvider);
        grid.getDataProvider().refreshAll();
    }

    private void setupSearchField(){
        searchField.addValueChangeListener(e -> {
            filteredDataProvider.setFilter(e.getValue());
            setupGridDataProvider();
        });
    }


    private void setEditDeleteButtonsEnabled(final boolean indicator) {
        editButton.setEnabled(indicator);
        deleteButton.setEnabled(indicator);
    }

    public DataProvider<DTO, String> getDataProvider() {
        return dataProvider;
    }

    public Grid<DTO> getGrid() {
        return grid;
    }

    public Service getService() {
        return service;
    }
}
