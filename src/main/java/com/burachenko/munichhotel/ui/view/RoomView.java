package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = RoomView.NAME)
public class RoomView extends VerticalLayout implements View {

    static final String NAME = "room";

    public RoomView() {
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Room view", Notification.Type.HUMANIZED_MESSAGE);
    }
}
