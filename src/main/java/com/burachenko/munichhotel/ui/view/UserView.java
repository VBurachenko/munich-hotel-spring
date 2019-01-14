package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.service.UserService;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = UserView.NAME)
public class UserView extends AbstractEntityView<UserDto, UserService> {

    static final String NAME = "users";

    private static final String SEARCH_PLACEHOLDER = "email or telephone";

    @Autowired
    public UserView(final UserService userService) {
        super(userService);
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return SEARCH_PLACEHOLDER;
    }

    @Override
    protected Class<UserDto> getEntityClass() {
        return UserDto.class;
    }
}