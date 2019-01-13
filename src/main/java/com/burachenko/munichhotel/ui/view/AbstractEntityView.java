package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.AbstractDto;
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

abstract class AbstractEntityView<T extends AbstractDto> extends VerticalLayout implements View {

    private HorizontalLayout mainInstrumentsLayout = new HorizontalLayout();

    Button addButton = new Button("Add");
    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");
    Button clearSearchField = new Button(VaadinIcons.CLOSE_CIRCLE);

    TextField searchField = new TextField();

    private DataProvider<T, String> dataProvider;
    private Grid<T> grid;

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setupInstrumentsLayout();
        setupGrid();
    }

    protected abstract Class<T> getEntityClass();

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

    private void setupGrid(){
        grid = new Grid<>(getEntityClass());
        grid.addSelectionListener(selection -> {
            int selectedLinesCount = selection.getAllSelectedItems().size();
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

    }

    private void setEditDeleteButtonsEnabled(final boolean indicator) {
        editButton.setEnabled(indicator);
        deleteButton.setEnabled(indicator);
    }

    public DataProvider<T, String> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(final DataProvider<T, String> dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Grid<T> getGrid() {
        return grid;
    }
}
