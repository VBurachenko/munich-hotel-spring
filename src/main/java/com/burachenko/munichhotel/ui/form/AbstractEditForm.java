package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEditForm<DTO extends AbstractDto> extends FormLayout {

    @Autowired
    private AbstractService service;
    private DTO abstractDto;

    private Binder<DTO> binder;
    private HorizontalLayout controlsLayout = new HorizontalLayout();
    private Button saveButton;

    public AbstractEditForm(final DTO abstractDto) {
        setMargin(true);
        this.abstractDto = abstractDto;
        binder = new Binder<>();
        binder.setBean(abstractDto);
        removeAllComponents();
        bindEntityFields();
        setupControls();
    }

    protected abstract void bindEntityFields();

    private void setupControls() {
        saveButton = new Button("Save");
        saveButton.addClickListener(click -> {
            try {
                final DTO abstractDto = getAbstractDto();
                getBinder().writeBean(abstractDto);
                final DTO savedDto = (DTO) getService().save(abstractDto);
                if (savedDto != null){
                    getBinder().setBean(savedDto);
                } else {
                    Notification.show("Saving error", Notification.Type.ERROR_MESSAGE);
                }
            } catch (ValidationException e) {
                Notification.show("Not valid data", Notification.Type.ERROR_MESSAGE);
            }

        });
        controlsLayout.addComponents(saveButton);
        addComponent(controlsLayout);
    }

    public DTO getAbstractDto() {
        return abstractDto;
    }

    public Binder<DTO> getBinder() {
        return binder;
    }

    public AbstractService getService() {
        return service;
    }
}
