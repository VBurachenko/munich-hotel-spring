package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEditForm extends FormLayout {

    private Binder<UserDto> userBinder;
    private Button saveButton;
    private Button closeButton;
    private Runnable onSaveOnClose;


    @Autowired
    private UserService userService;
    private UserDto userDto;

    public UserEditForm() {
        setMargin(true);
    }

    public void init(UserDto userDto){
        this.userDto = userDto;
        this.userBinder = new Binder<>();
        this.userBinder.setBean(userDto);
        removeAllComponents();
        initForm();
    }

    private void initForm() {

    }
}
