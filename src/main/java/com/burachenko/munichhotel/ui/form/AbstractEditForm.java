package com.burachenko.munichhotel.ui.form;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.service.AbstractService;
import com.vaadin.data.Binder;
import com.vaadin.data.ReadOnlyHasValue;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEditForm<DTO extends AbstractDto> extends FormLayout {

    @Autowired
    private AbstractService service;

    private DTO dto;

    private Binder<DTO> binder;
    private HorizontalLayout controlsLayout = new HorizontalLayout();
    private Button saveButton;

    public AbstractEditForm(final DTO dto) {
        setMargin(true);
        this.dto = dto;
        binder = new Binder<>();
        binder.setBean(dto);
        removeAllComponents();
        bindID();
        bindEntityFields();
        setupControls();
    }

    protected abstract void bindEntityFields();

    private void bindID(){
        final Label label = new Label();
        label.setCaption("Id");
        ReadOnlyHasValue<DTO> hasValue = new ReadOnlyHasValue<>(
                dto -> label.setValue(String.valueOf(dto.getId())));
        binder.forField(hasValue).bind(dto -> dto, null);
        addComponent(label);
    }

    private void setupControls() {
        saveButton = new Button("Save");
        saveButton.addClickListener(click -> {
            try {
                final DTO abstractDto = getDto();
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


    public DTO getDto() {
        return dto;
    }

    public Binder<DTO> getBinder() {
        return binder;
    }

    public AbstractService getService() {
        return service;
    }
}
