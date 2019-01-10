package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    static final String NAME = "user";

    @Autowired
    private UserService userService;

    private UserEditForm userForm;
    private Grid<UserDto> userGrid;
    private Button editButton;
    private Button deleteButton;


    public UserView() {
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        addControls();
        setupGrid();
        Notification.show("User view", Notification.Type.HUMANIZED_MESSAGE);
    }

    private void setupGrid() {

    }

    private void addControls() {

    }


}
