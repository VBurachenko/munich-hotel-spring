package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.enumeration.UserRole;
import com.burachenko.munichhotel.ui.annotation.AllowedTo;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import static com.burachenko.munichhotel.ui.view.MainView.NAME;

@SpringView(name = NAME)
@AllowedTo(value = UserRole.MODER)
public class MainView extends VerticalLayout implements View {

    public static final String NAME = "main";

    private final MenuBar menuBar = new MenuBar();

    public MainView() {
        setSizeFull();
        addComponent(menuBar);
        setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(final ViewChangeListener.ViewChangeEvent event) {
        menuBar.addItem("Users",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(UserView.NAME));
        menuBar.addItem("Rooms",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(RoomView.NAME));
        menuBar.addItem("Bookings",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(BookingView.NAME));
        menuBar.addItem("Invoices",
                (MenuBar.Command) selectedItem -> getUI().getNavigator().navigateTo(InvoiceView.NAME));
        Notification.show("Main view", Notification.Type.HUMANIZED_MESSAGE);
    }
}
