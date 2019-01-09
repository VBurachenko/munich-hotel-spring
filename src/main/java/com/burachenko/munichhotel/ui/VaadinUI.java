package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI(path = "/vaadin")
@Theme("valo")
public class VaadinUI extends UI{

    @Autowired
    private UserService userService;

    private Grid<UserDto> grid = new Grid<UserDto>(UserDto.class);

    private TextField filterText = new TextField();

    private UserForm userForm;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        userForm = new UserForm(this, userService);
        final VerticalLayout layout = new VerticalLayout();

        filterText.setCaption("filter by email");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        final Button addUserBtn = new Button("Add new user");

        addUserBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userForm.setUserDto(new UserDto());
        });

        final HorizontalLayout toolbar = new HorizontalLayout(filtering, addUserBtn);

        grid.setColumns("id", "email", "password", "name", "surname", "telNum", "birthday", "discount", "genderMale", "blocking", "role");

        final HorizontalLayout main = new HorizontalLayout(grid, userForm);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        updateList();

        setContent(layout);

        userForm.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(e -> {
            if (e.getValue() == null){
                userForm.setVisible(false);
            } else {
                userForm.setUserDto(e.getValue());
            }
        });
    }

    void updateList() {
        final List<UserDto> userList = userService.getUserList(filterText.getValue());
        grid.setItems(userList);
    }

}
