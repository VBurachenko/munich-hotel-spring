package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = UserView.NAME)
public class UserView extends VerticalLayout implements View {

    static final String NAME = "user";

    public UserView() {
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
