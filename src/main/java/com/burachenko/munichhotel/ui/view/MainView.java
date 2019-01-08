package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.editor.UserEditor;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@SpringView(name = MainView.NAME)
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "main";

    private final UserService userService;

    private UserEditor userEditor;

    private final Grid<UserDto> grid;

    TextField filter;

    private Button addNewBtn;

    @Autowired
    public MainView(final UserService userService, final UserEditor userEditor) {
        this.userService = userService;
        this.userEditor = userEditor;
        this.grid = new Grid<>(UserDto.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New user", VaadinIcons.PLUS);

        final HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        addComponents(actions, grid, userEditor);

        grid.setHeight("200px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumn("id").setWidth(50.0);

        filter.setPlaceholder("Filter by email");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> getUserList(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> userEditor.editUser(new UserDto()));

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(false);
            getUserList(filter.getValue());
        });

        getUserList(null);
    }


    public MainView(final UserService userService) {
        this.userService = userService;
        this.grid = new Grid<>(UserDto.class);
        addComponent(grid);
        getUserList();
    }

    private void getUserList(){
        grid.setItems(userService.getUserList());
    }

    private void getUserList(final String filterText){
        if (StringUtils.isEmpty(filterText)){
            getUserList();
        } else {
            grid.setItems(userService.findUserByEmail(filterText));
        }
    }


    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
