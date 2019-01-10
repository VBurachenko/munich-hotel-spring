package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.view.UserView;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@SpringComponent
@ViewScope
public class UserEditForm extends FormLayout {

    private Button saveButton;

    private UserService userService;
    private UserDto userDto;


    private UserView userView;
    private Binder <UserDto> userBinder;

    private Runnable onSaveOrClose;

    ////////////////////////////////////////////////

    private TextField email = new TextField("Email");
    private TextField telNum = new TextField("Telephone");
    private PasswordField password = new PasswordField("Password");

    private NativeSelect<UserBlocking> blocking = new NativeSelect<>("Blocking");

    private DateField birthday = new DateField("Birthday");

    private Button hide = new Button("Hide");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    public UserEditForm(UserView userView, UserService userService) {
        setMargin(true);


//        this.userView = userView;
//        this.userService = userService;
//
//        setSizeUndefined();
//
//        final HorizontalLayout upperButtons = new HorizontalLayout(hide);
//        final VerticalLayout fields = new VerticalLayout(email, telNum, password, blocking, birthday);
//        final HorizontalLayout buttons = new HorizontalLayout(save, delete);
//
//        addComponents(upperButtons, fields,  buttons);
//
//        blocking.setItems(UserBlocking.values());
//
//        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
//        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
//
//        bindInputs();
//
//        hide.addClickListener(e -> hideUserForm());
//        save.addClickListener(e -> save());
//        delete.addClickListener(e -> delete());

    }

    public void init(UserDto userDto){
        this.userDto = userDto;
        this.userBinder = new Binder<>();
        this.userBinder.setBean(userDto);
        removeAllComponents();
        initForm();
        addControls();
    }

    private void addControls() {
        final HorizontalLayout controlsLayout = new HorizontalLayout();
        saveButton = new Button("Save");
        saveButton.addClickListener(click -> {
            userDto = new UserDto();
            try {
                userBinder.writeBean(userDto);
                saveUser();
                if (onSaveOrClose != null){
                    onSaveOrClose.run();
                }
            } catch (ValidationException e) {
                Notification.show("User vaelidation error", Notification.Type.ERROR_MESSAGE);
            }
        });

        Button closeButton = new Button("Close");
        closeButton.addClickListener(click -> {
            userBinder.removeBean();
            if (onSaveOrClose != null){
                onSaveOrClose.run();
            }
        });

        controlsLayout.addComponents(saveButton, closeButton);
        addComponent(controlsLayout);
    }

    private void saveUser(){
        final UserDto savedUser = userService.registerNewUser(userDto);
        userBinder.setBean(savedUser);
    }

    public void initForm(){
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

        userBinder.readBean(userDto);    }

    public void bindInputs(){

//        userBinder.forField(email)
//                .withValidator(str -> !str.isEmpty(), "Mandatory field")
//                .withValidator(str -> str.length() < 5, "Must be more than 5 chars")
//                .bind(UserDto::getEmail, UserDto::setEmail);
//
//        userBinder.forField(telNum)
//                .withValidator(str -> !str.isEmpty(), "Mandatory field")
//                .bind(UserDto::getTelNum, UserDto::setTelNum);
//
//        userBinder.bind(password, UserDto::getPassword, UserDto::setPassword);
//        userBinder.bind(blocking, UserDto::getBlocking, UserDto::setBlocking);
//        userBinder.bind(birthday, UserDto::getBirthday, UserDto::setBirthday);
//
//        userBinder.readBean(userDto);
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
        userView.updateList();
        setVisible(false);
    }

    private void save(){
        if (userService.registerNewUser(userDto) == null){
            userService.updateUser(userDto);
        }
        userView.updateList();
        setVisible(false);
    }

    private void hideUserForm() {
        userBinder.removeBean();
        setVisible(false);
    }
}
