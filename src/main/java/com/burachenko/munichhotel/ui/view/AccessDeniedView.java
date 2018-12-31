package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;

@SpringView(name = AccessDeniedView.NAME)
public class AccessDeniedView extends CustomComponent implements View {
    public static final String NAME = "accessDenied";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
