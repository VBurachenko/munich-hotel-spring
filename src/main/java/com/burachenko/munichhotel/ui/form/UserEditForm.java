package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.enumeration.UserGender;
import com.burachenko.munichhotel.enumeration.UserRole;
import com.vaadin.ui.DateField;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

public class UserEditForm extends AbstractEditForm<UserDto>{

    public UserEditForm(final UserDto userDto) {
        super(userDto);
    }

    @Override
    protected void bindEntityFields() {
        bindEmailField();
        bindPasswordField();
        getBindNameField();
        getBindSurnameField();
        getBindTelNumField();
        getBindBirthdayField();
        getBindDiscountField();
        getBindGenderSelector();
        getBindBlockingSelector();
        getBindRoleSelector();
    }

    private void bindEmailField(){
        final TextField emailField = new TextField("Email");
        getBinder()
                .forField(emailField)
                .bind(UserDto::getEmail, UserDto::setEmail);
        addComponent(emailField);
    }

    private void bindPasswordField(){
        final PasswordField passwordField = new PasswordField("Password");
        getBinder()
                .forField(passwordField)
                .bind(UserDto::getPassword, UserDto::setPassword);
        addComponent(passwordField);
    }

    private void getBindNameField() {
        final TextField nameField = new TextField("Name");
        getBinder()
                .forField(nameField)
                .bind(UserDto::getName, UserDto::setName);
        addComponent(nameField);
    }

    private void getBindSurnameField() {
        final TextField surnameField = new TextField("Surname");
        getBinder()
                .forField(surnameField)
                .bind(UserDto::getSurname, UserDto::setSurname);
        addComponent(surnameField);
    }

    private void getBindTelNumField() {
        final TextField telNumField = new TextField("Tel. number");
        getBinder()
                .forField(telNumField)
                .bind(UserDto::getTelNum, UserDto::setTelNum);
        addComponent(telNumField);
    }

    private void getBindBirthdayField() {
        final DateField birthdayField = new DateField("Birthday");
        getBinder()
                .forField(birthdayField)
                .bind(UserDto::getBirthday, UserDto::setBirthday);
        addComponent(birthdayField);
    }

    private void getBindDiscountField() {
        final TextField discountField = new TextField("Discount");
        getBinder()
                .forField(discountField)
                .withConverter(Integer::valueOf, String::valueOf)
                .bind(UserDto::getDiscount, UserDto::setDiscount);
        addComponent(discountField);
    }

    private void getBindGenderSelector() {
        final RadioButtonGroup<UserGender> selectGender = new RadioButtonGroup<>("Gender");
        selectGender.setItems(UserGender.values());
        selectGender.setSelectedItem(UserGender.M);
        getBinder()
                .forField(selectGender)
                .bind(UserDto::getGenderMale, UserDto::setGenderMale);
        addComponent(selectGender);
    }

    private void getBindBlockingSelector() {
        final NativeSelect<UserBlocking> selectBlocking = new NativeSelect<>("Blocking");

        selectBlocking.setItems(UserBlocking.values());
        selectBlocking.setEmptySelectionAllowed(false);
        selectBlocking.setSelectedItem(UserBlocking.NONE);

        getBinder().forField(selectBlocking).bind(UserDto::getBlocking, UserDto::setBlocking);

        addComponent(selectBlocking);
    }

    private void getBindRoleSelector() {
        final NativeSelect<UserRole> selectRole = new NativeSelect<>("Role");

        selectRole.setItems(UserRole.values());
        selectRole.setEmptySelectionAllowed(false);
        selectRole.setSelectedItem(UserRole.GUEST);

        getBinder().forField(selectRole).bind(UserDto::getRole, UserDto::setRole);

        addComponent(selectRole);
    }


}
