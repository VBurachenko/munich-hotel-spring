package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.HotelUI;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UserForm extends FormLayout {

    private TextField email = new TextField("Email");
    private TextField telNum = new TextField("Telephone");
    private PasswordField password = new PasswordField("Password");

    private NativeSelect<UserBlocking> blocking = new NativeSelect<>("Blocking");

    private DateField birthday = new DateField("Birthday");

    private Button hide = new Button("Hide");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private UserService userService;
    private UserDto userDto;

    private HotelUI hotelUI;

    private Binder <UserDto> userBinder = new Binder<>(UserDto.class);

    public UserForm(HotelUI hotelUI, UserService userService) {
        this.hotelUI = hotelUI;
        this.userService = userService;

        setSizeUndefined();

        final HorizontalLayout upperButtons = new HorizontalLayout(hide);
        final VerticalLayout fields = new VerticalLayout(email, telNum, password, blocking, birthday);
        final HorizontalLayout buttons = new HorizontalLayout(save, delete);

        addComponents(upperButtons, fields,  buttons);

        blocking.setItems(UserBlocking.values());

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        bindInputs();

        hide.addClickListener(e -> hideUserForm());
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());

    }

    public void bindInputs(){

        userBinder.forField(email)
                .withValidator(str -> !str.isEmpty(), "Mandatory field")
                .withValidator(str -> str.length() < 5, "Must be more than 5 chars")
                .bind(UserDto::getEmail, UserDto::setEmail);

        userBinder.forField(telNum)
                .withValidator(str -> !str.isEmpty(), "Mandatory field")
                .bind(UserDto::getTelNum, UserDto::setTelNum);

        userBinder.bind(password, UserDto::getPassword, UserDto::setPassword);
        userBinder.bind(blocking, UserDto::getBlocking, UserDto::setBlocking);
        userBinder.bind(birthday, UserDto::getBirthday, UserDto::setBirthday);

        userBinder.readBean(userDto);
    }

    public void passwordFieldReadOnly(final boolean editFactor){
        password.setReadOnly(editFactor);
    }

    public void setUserDto(final UserDto userDto){
        this.userDto = userDto;
        passwordFieldReadOnly(userDto.isPersisted());
        userBinder.setBean(userDto);
        delete.setVisible(userDto.isPersisted());
        setVisible(true);
        email.selectAll();
    }

    private void delete(){
        userService.deleteUser(userDto);
        hotelUI.updateList();
        setVisible(false);
    }

    private void save(){
        if (userService.registerNewUser(userDto) == null){
            userService.updateUser(userDto);
        }
        hotelUI.updateList();
        setVisible(false);
    }

    private void hideUserForm() {
        userBinder.removeBean();
        setVisible(false);
    }
}
