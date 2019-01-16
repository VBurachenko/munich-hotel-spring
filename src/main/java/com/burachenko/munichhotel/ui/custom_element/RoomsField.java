package com.burachenko.munichhotel.ui.custom_element;

import com.burachenko.munichhotel.dto.RoomDto;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class RoomsField extends CustomField<List<RoomDto>> {

    private List<RoomDto> value;
    private VerticalLayout listLayout = new VerticalLayout();

    @Override
    protected Component initContent() {
        return null;
    }

    @Override
    protected void doSetValue(final List<RoomDto> value) {
        if (value == null){
            this.value = new ArrayList<>();
        } else {
            this.value = new ArrayList<>(value);
        }

    }

    @Override
    public List<RoomDto> getValue() {
        return null;
    }

    private ListDataProvider<RoomDto> getDataProvider(){
        final ListDataProvider<RoomDto> dataProvider = new ListDataProvider<>(value);
        return dataProvider;
    }
}
