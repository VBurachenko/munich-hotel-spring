package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = UserView.NAME)
public class UserView implements View {

    static final String NAME = "user";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
