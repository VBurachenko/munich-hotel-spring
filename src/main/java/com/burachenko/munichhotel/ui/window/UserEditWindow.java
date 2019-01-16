package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEditWindow extends AbstractEditWindow<UserDto> {

    private UserEditForm userEditForm;

    @Autowired
    public UserEditWindow(final UserEditForm userEditForm) {
        super(userEditForm);
        this.userEditForm = userEditForm;
    }
}
