package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

@Push
@SpringUI(path = "munich-hotel")
@Theme("mytheme")
public class VaadinUI extends UI{

    private final UserService userService;

    private final Grid<UserDto> userGrid = new Grid<>(UserDto.class);

    public VaadinUI(final UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void init(final VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();

        layout.addComponents(userGrid);

        updateList();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");
        final Button button = new Button("Click me");

        button.addClickListener(e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
        });

        layout.addComponents(name, button);
        setContent(layout);
    }

    private void updateList() {
        final List<UserDto> userList = userService.getUsersList();
        userGrid.setItems(userList);
    }
}
