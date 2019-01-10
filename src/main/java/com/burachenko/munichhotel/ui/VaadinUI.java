package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.form.UserForm;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI(path = "/vaadin")
@Theme("valo")
public class VaadinUI extends UI implements ViewAccessControl {

    @Autowired
    private UserService userService;

    private Grid<UserDto> grid = new Grid<UserDto>(UserDto.class);

    private TextField filterText = new TextField();

    private UserForm userForm;

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
        userForm = new UserForm(this, userService);
        final VerticalLayout layout = new VerticalLayout();

//        filterText.setCaption("Search");
        filterText.setPlaceholder("email or telephone");
        filterText.setIcon(VaadinIcons.SEARCH);
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());
        clearFilterTextBtn.setClickShortcut(ShortcutAction.KeyCode.DELETE);

        CssLayout filtering = new CssLayout();
        filtering.setSizeFull();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        final Button Options = new Button("Add new user");

        Options.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userForm.setUserDto(new UserDto());
        });

        final HorizontalLayout toolbar = new HorizontalLayout(filtering, Options);

        grid.setColumns("id", "email", "password", "name", "surname", "telNum", "birthday", "discount", "genderMale", "blocking", "role");

        final HorizontalLayout main = new HorizontalLayout(grid, userForm);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(toolbar, main);

        updateList();

        setContent(layout);

        userForm.setVisible(false);

        expandGrid();
    }

    public void expandGrid() {
        grid.asSingleSelect().addValueChangeListener(e -> {
            if (e.getValue() == null){
                userForm.setVisible(false);
            } else {
                userForm.setUserDto(e.getValue());
            }
        });
    }

    public void updateList() {
        final List<UserDto> userList = userService.getUserList(filterText.getValue());
        grid.setItems(userList);
    }

    @Override
    public boolean isAccessGranted(final UI ui, final String s) {
        if ("notAvailableView".equals(s)){
            return false;
        }
        return true;
    }
}
