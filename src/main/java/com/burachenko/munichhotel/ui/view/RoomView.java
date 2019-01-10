package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

@SpringView(name = RoomView.NAME)
public class RoomView implements View {

    static final String NAME = "room";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {

    }
}
