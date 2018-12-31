package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.ui.view.AccessDeniedView;
import com.burachenko.munichhotel.ui.view.ErrorView;
import com.burachenko.munichhotel.ui.view.MainView;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

@Push
@SpringUI(path = "munich-hotel")
@Theme("mytheme")
public class VaadinUI extends UI implements ViewAccessControl {

    private final SpringViewProvider viewProvider;

    public VaadinUI(final SpringViewProvider viewProvider) {
        this.viewProvider = viewProvider;
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
    }

    @Override
    public boolean isAccessGranted(final UI ui, final String beanName) {
        return !"notAvailableView".equals(beanName);
    }

    @Override
    protected void init(final VaadinRequest request) {
        final Navigator navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(new ErrorView());
        navigator.navigateTo(MainView.NAME);
    }
}
