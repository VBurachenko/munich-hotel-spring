package com.burachenko.munichhotel.ui.window;

import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.ui.form.InvoiceEditFrom;
import org.springframework.beans.factory.annotation.Autowired;

public class InvoiceEditWindow extends AbstractEditWindow<InvoiceDto> {

    private InvoiceEditFrom invoiceEditFrom;

    @Autowired
    public InvoiceEditWindow(final InvoiceEditFrom invoiceEditFrom) {
        super(invoiceEditFrom);
        this.invoiceEditFrom = invoiceEditFrom;
    }

}
