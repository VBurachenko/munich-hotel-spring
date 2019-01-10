package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.VaadinUI;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UserForm extends FormLayout {

    private TextField email = new TextField("Email");
    private TextField telNum = new TextField("Telephone");
    private TextField password = new TextField("Password");

    private NativeSelect<UserBlocking> blocking = new NativeSelect<>("Blocking");

    private DateField birthday = new DateField("Birthday");

    private Label optionsLabel = new Label("Options");

    private Button hide = new Button("Hide");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Button edit = new Button("Edit");

    private UserService userService;
    private UserDto userDto;

    private VaadinUI vaadinUI;

    private Binder <UserDto> userBinder = new Binder<>(UserDto.class);

    public UserForm(VaadinUI vaadinUI, UserService userService) {
        this.vaadinUI = vaadinUI;
        this.userService = userService;

        setSizeUndefined();

        final HorizontalLayout upperButtons = new HorizontalLayout(optionsLabel, hide);
        final VerticalLayout fields = new VerticalLayout(email, telNum, password, blocking, birthday);
        final HorizontalLayout buttons = new HorizontalLayout(save, delete, edit);
        addComponents(upperButtons, fields,  buttons);

        blocking.setItems(UserBlocking.values());

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        userBinder.bind(email, UserDto::getEmail, UserDto::setEmail);
        userBinder.bind(telNum, UserDto::getTelNum, UserDto::setTelNum);
        userBinder.bind(password, UserDto::getPassword, UserDto::setPassword);
        userBinder.bind(blocking, UserDto::getBlocking, UserDto::setBlocking);
        userBinder.bind(birthday, UserDto::getBirthday, UserDto::setBirthday);

        userBinder.readBean(userDto);

        hide.addClickListener(e -> hideUserForm());
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        edit.addClickListener(e -> update());

    }

    private void hideUserForm() {
        userBinder.removeBean();
        setVisible(false);
    }

    public void setUserDto(final UserDto userDto){
        this.userDto = userDto;
        userBinder.setBean(userDto);

        delete.setVisible(userDto.isPersisted());
        edit.setVisible(userDto.isPersisted());
        setVisible(true);
        email.selectAll();
    }

    private void delete(){
        userService.deleteUser(userDto);
        vaadinUI.updateList();
        setVisible(false);
    }

    private void save(){
        System.out.println(userDto);
        userService.registerNewUser(userDto);
        vaadinUI.updateList();
        setVisible(false);
    }

    private void update(){
        userService.updateUser(userDto);
        vaadinUI.updateList();
        setVisible(false);
    }

}
