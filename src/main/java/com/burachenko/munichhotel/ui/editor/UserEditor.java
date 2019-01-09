package com.burachenko.munichhotel.ui.editor;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout{

    private final UserService userService;

    private UserDto userDto;

    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Last name");

    private final Button save = new Button("Save", VaadinIcons.PLUS);
    private final Button cancel = new Button("Cancel");
    private final Button delete = new Button("Delete", VaadinIcons.TRASH);

    private final Binder<UserDto> binder = new Binder<>(UserDto.class);

    private ChangeHandler changeHandler;

    @Autowired
    public UserEditor(final UserService userService) {
        this.userService = userService;

        final HorizontalLayout actionsBar = new HorizontalLayout(save, delete, cancel);

        addComponents(firstName, lastName, actionsBar);

        binder.bindInstanceFields(this);

        setSpacing(true);

//        addKeyPressListener(Key.ENTER, new ComponentEventListener<KeyPressEvent>() {
//            @Override
//            public void onComponentEvent(final KeyPressEvent e) {
//                UserEditor.this.save();
//            }
//        });

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editUser(userDto));
        setVisible(false);
    }

    private void save() {
        userService.registerNewUser(userDto);
        changeHandler.onChange();
    }

    private void delete() {
        userService.deleteUser(userDto.getId());
        changeHandler.onChange();
    }

    public final void editUser(final UserDto userDto){
        if (userDto == null){
            setVisible(false);
            return;
        }
        final boolean persisted = userDto.getId() != null;
        if (persisted){
            this.userDto = userService.getUser(userDto.getId());
        } else {
            this.userDto = userDto;
        }
        cancel.setVisible(persisted);
        binder.setBean(this.userDto);
        setVisible(true);
        firstName.focus();
    }

    public interface ChangeHandler{
        void onChange();
    }

    public void setChangeHandler(final ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }
}
