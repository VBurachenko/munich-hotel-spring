package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.InvoiceConverter;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceConverter invoiceConverter;


    public List<InvoiceEntity> getInvoicesList(){
        return invoiceRepository.findAll();
    }

    public void createInvoice(final InvoiceEntity invoice){
        invoiceRepository.save(invoice);
    }
}
