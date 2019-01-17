package com.burachenko.munichhotel.ui.custom_element;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class ListField<DTO extends AbstractDto> extends CustomField<List<DTO>> {

    private List<DTO> value;
    private VerticalLayout listLayout = new VerticalLayout();
    private Supplier<List<DTO>> supplier;

    public ListField(final Supplier<List<DTO>> supplier) {
        this.supplier = supplier;
        listLayout.setMargin(false);
    }

    @Override
    protected Component initContent() {
        redraw();
        return listLayout;
    }

    @Override
    protected void doSetValue(final List<DTO> value) {
        if (value == null){
            this.value = new ArrayList<>();
        } else {
            this.value = new ArrayList<>(value);
        }
        redraw();
    }

    private void redraw() {
        listLayout.removeAllComponents();
    }

    @Override
    public List<DTO> getValue() {
        return value == null ? Collections.emptyList() : value;
    }

    private ListDataProvider<DTO> getDataProvider(){
        final ListDataProvider<DTO> dataProvider = new ListDataProvider<>(value);
        dataProvider.addFilter(t -> {
            final Long id = t.getId();
            return getValue().stream().map(DTO::getId).noneMatch(id::equals);
        });
        return dataProvider;
    }
}
