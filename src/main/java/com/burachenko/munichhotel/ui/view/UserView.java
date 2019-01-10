package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.container.UserEditContainer;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    static final String NAME = "user";

    @Autowired
    private UserService userService;

    private UserEditForm userForm = new UserEditForm();

    private UserEditContainer editContainer = new UserEditContainer(userForm);


    private Grid<UserDto> userGrid = new Grid<>(UserDto.class);
    private Button editButton;
    private Button deleteButton;
    private Button addButton;


    public UserView() {
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        editContainer.setOnClose(() -> {
            userGrid.deselectAll();
            userGrid.getDataProvider().refreshAll();
        });
        addControls();
        setupGrid();
        Notification.show("User view", Notification.Type.HUMANIZED_MESSAGE);
    }

    private void setupGrid() {
        addComponent(userGrid);
        setExpandRatio(userGrid, 30);
    }

    private void addControls() {
        final HorizontalLayout outerLayout = new HorizontalLayout();
        outerLayout.setWidth(100, Unit.PERCENTAGE);
        outerLayout.setMargin(false);
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(false);
        outerLayout.addComponent(layout);
        addButton = new Button("Add");
        deleteButton = new Button("Delete");
        editButton = new Button("Edit");
        layout.addComponents(addButton, editButton, deleteButton);

        addComponent(outerLayout);
        setExpandRatio(outerLayout, 1);

    }

}
