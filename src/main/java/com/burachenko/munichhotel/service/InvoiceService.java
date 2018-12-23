package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.entity.Invoice;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoicesList(){
        return invoiceRepository.findAll();
    }

    public void createInvoice(final Invoice invoice){
        invoiceRepository.save(invoice);
    }
}
