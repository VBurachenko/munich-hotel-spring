package com.burachenko.munichhotel.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

import static com.burachenko.munichhotel.ui.view.MainView.NAME;

@SpringView(name = NAME)
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "main";

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        final MenuBar menuBar = new MenuBar();
        menuBar.addItem("Users",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(UserView.NAME));
        menuBar.addItem("Rooms",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(RoomView.NAME));
        menuBar.addItem("Bookings",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(BookingView.NAME));

        addComponent(menuBar);
    }
}
