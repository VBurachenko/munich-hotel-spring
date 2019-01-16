package com.burachenko.munichhotel.ui.view;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.service.InvoiceService;
import com.burachenko.munichhotel.ui.form.InvoiceEditFrom;
import com.burachenko.munichhotel.ui.window.AbstractEditWindow;
import com.burachenko.munichhotel.ui.window.InvoiceEditWindow;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;


@SpringView(name = InvoiceView.NAME)
public class InvoiceView extends AbstractEntityView<InvoiceDto, InvoiceService> {

    static final String NAME = "invoice";

    @Autowired
    public InvoiceView(final InvoiceService service) {
        super(service);
    }

    @Override
    protected Class<InvoiceDto> getDtoClass() {
        return InvoiceDto.class;
    }

    @Override
    protected String getSearchFieldPlaceholder() {
        return "invoice id";
    }

    @Override
    protected void addMoreInstruments(final HorizontalLayout layout) {

    }

    @Override
    protected AbstractEditWindow<InvoiceDto> getEditWindow(final InvoiceDto invoiceDto) {
        return new InvoiceEditWindow(new InvoiceEditFrom(invoiceDto));
    }
}
