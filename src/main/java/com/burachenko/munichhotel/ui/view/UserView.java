package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.burachenko.munichhotel.ui.form.UserEditForm;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    static final String NAME = "users";

    @Autowired
    private UserService userService;

    private Grid<UserDto> grid = new Grid<UserDto>(UserDto.class);

    private TextField filterText = new TextField();

    private Button editButton = new Button("Edit");

    private Button deleteButton = new Button("Delete");

    private Button userViewBtn = new Button("Next");

    private UserEditForm userEditForm;

    public UserView() {
        setSizeFull();

    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

        setSizeFull();

        addControls();

        setupGrid();



//        userEditForm = new UserEditForm(this, userService);
//
//        final VerticalLayout generalLayout = new VerticalLayout();
//
//        this.addComponent(generalLayout);
//
//        filterText.setPlaceholder("email or telephone");
//        filterText.addValueChangeListener(e -> updateList());
//        filterText.setValueChangeMode(ValueChangeMode.LAZY);
//
//        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
//        clearFilterTextBtn.setDescription("Clear the current filter");
//        clearFilterTextBtn.addClickListener(e -> filterText.clear());
//        clearFilterTextBtn.setClickShortcut(ShortcutAction.KeyCode.DELETE);
//
//        CssLayout filtering = new CssLayout();
//        filtering.setSizeFull();
//        filtering.addComponents(filterText, clearFilterTextBtn);
//        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//
//        final Button options = new Button("Add new user");
//
//        options.addClickListener(e -> {
//            grid.asSingleSelect().clear();
//            userEditForm.setUserDto(new UserDto());
//            userEditForm.passwordFieldReadOnly(false);
//        });
//
//        final HorizontalLayout toolbar = new HorizontalLayout(filtering, options);
//
//        grid.setColumns("id", "email", "password", "name", "surname", "telNum", "birthday", "discount", "genderMale", "blocking", "role");
//
//        final HorizontalLayout main = new HorizontalLayout(grid, userEditForm);
//        main.setSizeFull();
//        grid.setSizeFull();
//        main.setExpandRatio(grid, 1);
//
//        generalLayout.addComponents(toolbar, main);
//
//        updateList();
//
//        userEditForm.setVisible(false);
//
//        grid.asSingleSelect().addValueChangeListener(e -> {
//            if (e.getValue() == null){
//                userEditForm.setVisible(false);
//            } else {
//                userEditForm.setUserDto(e.getValue());
//            }
//        });
    }

    private void addControls() {

        final HorizontalLayout outerLayout = new HorizontalLayout();
        outerLayout.setWidth(100, Unit.PERCENTAGE);
        outerLayout.setMargin(false);

        final HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(false);
        outerLayout.addComponent(layout);

        final Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            userEditForm.init(new UserDto());
        });
    }

    private void setupGrid(){
        setupGridDataProvider();
        grid.addSelectionListener(selection -> {
            final int selectedUsersSize = selection.getAllSelectedItems().size();
            editButton.setEnabled(selectedUsersSize == 1);
            deleteButton.setEnabled(selectedUsersSize > 0);
        });
        addComponent(grid);
        setExpandRatio(grid, 30);
    }

    private void setupGridDataProvider(){
        CallbackDataProvider<UserDto, String> dataProvider = new CallbackDataProvider<>(
                q -> userService.getUserList(q),
                q -> (int) userService.count());
        grid.setDataProvider(dataProvider);
        grid.getDataProvider().refreshAll();
    }

    public void updateList() {
        final List<UserDto> userList = userService.getUserList(filterText.getValue());
        grid.setItems(userList);
    }
}
