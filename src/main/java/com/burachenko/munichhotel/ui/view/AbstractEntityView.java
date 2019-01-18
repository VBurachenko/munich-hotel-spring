package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.burachenko.munichhotel.ui.window.AbstractEditWindow;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.components.grid.MultiSelectionModel.SelectAllCheckBoxVisibility;

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
    private Grid<DTO> grid = new Grid<>(getDtoClass());

    public AbstractEntityView(final Service service) {
        this.service = service;
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setupInstrumentsLayout();
        setupGrid();
        setupInstruments();
    }

    protected abstract Class<DTO> getDtoClass();

    protected abstract String getSearchFieldPlaceholder();

    protected abstract void addMoreInstruments(final HorizontalLayout layout);

    protected abstract AbstractEditWindow<DTO> getEditWindow(final DTO dto);

    private void setupInstrumentsLayout() {
        searchField.setPlaceholder(getSearchFieldPlaceholder());
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        clearSearchField.setClickShortcut(ShortcutAction.KeyCode.DELETE);

        final CssLayout searchInstrumentsLayout = new CssLayout();
        searchInstrumentsLayout.addComponents(searchField, clearSearchField);

        mainInstrumentsLayout.addComponents(searchInstrumentsLayout, addButton, editButton, deleteButton);
        addMoreInstruments(mainInstrumentsLayout);
        setEditDeleteButtonsEnabled(false);

        addComponent(mainInstrumentsLayout);
    }

    private void setupInstruments() {
        addButton.addClickListener(click -> {
            try {
                final Window addingWindow = getEditWindow(getDtoClass().newInstance());
                addingWindow.addCloseListener(close -> dataProvider.refreshAll());
                getUI().addWindow(addingWindow);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Impossible to create instance of entity");
            }
        });
        editButton.addClickListener(click -> {
            final DTO dto = grid.getSelectedItems().iterator().next();
            final Window editingWindow = getEditWindow(dto);
            editingWindow.addCloseListener(close -> dataProvider.refreshAll());
            getUI().addWindow(editingWindow);
        });
        deleteButton.addClickListener(click -> {
            service.deleteAll(grid.getSelectedItems());
            grid.getDataProvider().refreshAll();
        });
        clearSearchField.addClickListener(click -> {
            searchField.clear();
            grid.getDataProvider().refreshAll();
        });
        setupSearchField();
    }

    private void setupGrid(){
        setupGridDataProvider();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ((MultiSelectionModel) grid.getSelectionModel())
                .setSelectAllCheckBoxVisibility(SelectAllCheckBoxVisibility.VISIBLE);
        grid.setSizeFull();
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
        setExpandRatio(grid, 1);
    }

    private void setupGridDataProvider(){
        dataProvider = DataProvider.fromFilteringCallbacks(
                query -> service.findByFilterQueryWithPagination(query),
                query -> (int) service.findByFilterQueryWithPagination(query).count()
        );

//        filteredDataProvider = DataProvider.fromFilteringCallbacks(
//                query -> service.findByFilterQueryWithPagination(query),
//                query -> (int) service.findByFilterQueryWithPagination(query).count()
//        ).withConfigurableFilter();
        filteredDataProvider = dataProvider.withConfigurableFilter();
        grid.setDataProvider(filteredDataProvider);
        grid.getDataProvider().refreshAll();
    }

    private void setupSearchField(){
        searchField.addValueChangeListener(e -> {
            filteredDataProvider.setFilter(e.getValue());
            grid.getDataProvider().refreshAll();
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
