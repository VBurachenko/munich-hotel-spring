package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.form.UserForm;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    static final String NAME = "users";

    @Autowired
    private UserService userService;

    private Grid<UserDto> grid = new Grid<UserDto>(UserDto.class);

    private TextField filterText = new TextField();

    private Button userViewBtn = new Button("Next");

    private UserForm userForm;

    public UserView() {
        setSizeFull();
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

        userForm = new UserForm(this, userService);

        final VerticalLayout generalLayout = new VerticalLayout();

        this.addComponent(generalLayout);

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

        final Button options = new Button("Add new user");

        options.addClickListener(e -> {
            grid.asSingleSelect().clear();
            userForm.setUserDto(new UserDto());
            userForm.passwordFieldReadOnly(false);
        });

        final HorizontalLayout toolbar = new HorizontalLayout(filtering, options);

        grid.setColumns("id", "email", "password", "name", "surname", "telNum", "birthday", "discount", "genderMale", "blocking", "role");

        final HorizontalLayout main = new HorizontalLayout(grid, userForm);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        generalLayout.addComponents(toolbar, main);

        updateList();

        userForm.setVisible(false);

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
}
