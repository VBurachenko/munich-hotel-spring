package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.service.RoomService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class RoomEditForm extends AbstractEditForm<RoomDto, RoomService>{

    public RoomEditForm(final RoomDto roomDto, final RoomService roomService) {
        super(roomDto, roomService);
    }

    @Override
    protected void bindEntityFields() {

    }
}
