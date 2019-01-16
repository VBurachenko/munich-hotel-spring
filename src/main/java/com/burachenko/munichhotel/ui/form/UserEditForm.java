package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserGender;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class UserEditForm extends AbstractEditForm<UserDto>{

    private TextField emailField = new TextField("Email");
    private PasswordField passwordField = new PasswordField("Password");
    private TextField nameField = new TextField("Name");
    private TextField surnameField = new TextField("Surname");
    private TextField telNumField = new TextField("Tel. number");
    private DateField birthdayField = new DateField("Birthday");

    private RadioButtonGroup<UserGender> checkGender = new RadioButtonGroup<>("Gender");


    public UserEditForm(final UserDto userDto) {
        super(userDto);
        checkGender.setItems(UserGender.values());
        checkGender.setSelectedItem(UserGender.M);
        addComponents(emailField, passwordField, nameField, surnameField, telNumField, birthdayField, checkGender);
    }

    @Override
    protected void bindEntityFields() {

    }
}
