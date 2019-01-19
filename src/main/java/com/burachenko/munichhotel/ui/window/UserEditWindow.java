package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class UserEditWindow extends AbstractEditWindow<UserDto, UserService> {

    @Autowired
    public UserEditWindow(final UserEditForm userEditForm) {
        super(userEditForm);
    }
}
