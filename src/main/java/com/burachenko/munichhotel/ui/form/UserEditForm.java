package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEditForm extends AbstractEditForm<UserDto>{

    @Autowired
    public UserEditForm(final UserDto userDto) {
        super(userDto);
    }

    @Override
    protected void bindEntityFields() {

    }
}
