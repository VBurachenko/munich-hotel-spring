package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.burachenko.munichhotel.ui.form.AbstractEditForm;

public class UserEditWindow extends AbstractEditWindow<UserDto>{

    private AbstractService service;

    public UserEditWindow(final AbstractEditForm<UserDto> editForm, final AbstractService service) {
        super(editForm);
        this.service = service;
    }
}
