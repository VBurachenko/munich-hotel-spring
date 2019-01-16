package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.ui.form.AbstractEditForm;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomEditWindow extends AbstractEditWindow<RoomDto> {


    @Autowired
    public RoomEditWindow(final AbstractEditForm<RoomDto> editForm) {
        super(editForm);
    }
}
