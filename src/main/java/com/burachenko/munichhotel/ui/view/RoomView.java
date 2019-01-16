package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.service.RoomService;
import com.burachenko.munichhotel.ui.form.RoomEditForm;
import com.burachenko.munichhotel.ui.window.AbstractEditWindow;
import com.burachenko.munichhotel.ui.window.RoomEditWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;

@SpringView(name = RoomView.NAME)
public class RoomView extends AbstractEntityView<RoomDto, RoomService> {

    static final String NAME = "room";

    private static final String SEARCH_PLACEHOLDER = "room id";

    public RoomView(final RoomService service) {
        super(service);
    }

    @Override
    protected Class<RoomDto> getEntityClass() {
        return RoomDto.class;
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return SEARCH_PLACEHOLDER;
    }

    @Override
    protected void addMoreInstruments(final HorizontalLayout layout) {

    }

    @Override
    protected AbstractEditWindow<RoomDto> getEditWindow(final RoomDto roomDto) {
        return new RoomEditWindow(new RoomEditForm(roomDto));
    }
}
