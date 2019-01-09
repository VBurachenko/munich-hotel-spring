package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class UserForm extends FormLayout {

    private TextField email = new TextField("Email");
    private TextField telNum = new TextField("Telephone");
    private TextField password = new TextField("Password");

    private NativeSelect<Integer> blocking = new NativeSelect<>("Blocking");

    private DateField birthday = new DateField("Birthday");

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private UserService userService;
    private UserDto userDto;

    private VaadinUI vaadinUI;

    private Binder <UserDto> userBinder = new Binder<>(UserDto.class);

    public UserForm(VaadinUI vaadinUI, UserService userService) {
        this.vaadinUI = vaadinUI;
        this.userService = userService;

        setSizeUndefined();

        final HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(email, telNum, password, blocking, birthday, buttons);

        blocking.setItems(new Integer[]{0, 1, 2});

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        userBinder.bindInstanceFields(this);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());

    }

    public void setUserDto(final UserDto userDto){
        this.userDto = userDto;
        userBinder.setBean(userDto);

        delete.setVisible(userDto.isPersisted());
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
        System.out.println(userService.registerNewUser(userDto));
        vaadinUI.updateList();
        setVisible(false);
    }
}
