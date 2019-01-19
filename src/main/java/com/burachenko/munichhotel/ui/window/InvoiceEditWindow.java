package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.service.InvoiceService;
import com.burachenko.munichhotel.ui.form.InvoiceEditFrom;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class InvoiceEditWindow extends AbstractEditWindow<InvoiceDto, InvoiceService> {

    @Autowired
    public InvoiceEditWindow(final InvoiceEditFrom invoiceEditFrom) {
        super(invoiceEditFrom);
    }

}
