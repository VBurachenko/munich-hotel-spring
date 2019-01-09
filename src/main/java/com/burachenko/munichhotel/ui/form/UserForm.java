package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.UserDto;
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

    private NativeSelect<Integer> blocking = new NativeSelect<>("Blocking");

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

        blocking.setItems(new Integer[]{0, 1, 2});

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        userBinder.bindInstanceFields(this);

        hide.addClickListener(e -> vaadinUI.expandGrid());
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        edit.addClickListener(e -> update());

    }

    public void setUserDto(final UserDto userDto){
        this.userDto = userDto;
        userBinder.setBean(userDto);

        delete.setVisible(userDto.isPersisted());
        edit.setVisible(userDto.isPersisted() && !email.getValue().isEmpty() && !password.isEmpty() && !telNum.isEmpty());
        setVisible(true);
        email.selectAll();
    }

    private void delete(){
        userService.deleteUser(userDto);
        vaadinUI.updateList();
        setVisible(false);
    }

    private void save(){
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
