package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.service.RoomService;
import com.burachenko.munichhotel.ui.form.AbstractEditForm;
import com.burachenko.munichhotel.ui.form.RoomEditForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class RoomEditWindow extends AbstractEditWindow<RoomDto, RoomService> {


    @Autowired
    public RoomEditWindow(final RoomEditForm roomEditForm) {
        super(roomEditForm);
    }
}
