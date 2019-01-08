package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Push
@SpringUI(path = "/vaadin")
@Theme("valo")
public class VaadinUI extends UI{

    @Autowired
    private UserService userService;

    private Grid<UserDto> grid = new Grid<UserDto>(UserDto.class);

    private TextField filterText = new TextField();

    @Override
    protected void init(final VaadinRequest vaadinRequest) {
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

        grid.setColumns("id", "email", "password", "telNum", "blocking", "role");
        layout.addComponents(filtering, grid);

        updateList();

        setContent(layout);
    }

    private void updateList() {
        List<UserDto> userList = userService.getUserList(filterText.getValue());
        grid.setItems(userList);
    }

}
