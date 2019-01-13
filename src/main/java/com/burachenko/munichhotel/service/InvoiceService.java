package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.InvoiceConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import com.burachenko.munichhotel.service.util.DatesCalculator;
import com.burachenko.munichhotel.service.util.PaymentCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService extends AbstractService<InvoiceDto, InvoiceEntity, InvoiceRepository>{

    public InvoiceService(final InvoiceRepository invoiceRepository, final InvoiceConverter invoiceConverter) {
        super(invoiceRepository, invoiceConverter);
    }

    @Override
    protected boolean beforeSave(final InvoiceDto dto) {
        return true;
    }

    public List<InvoiceEntity> getInvoicesList(){
        return getRepository().findAll();
    }

    public InvoiceDto getInvoice(final Long id){
        final Optional<InvoiceEntity> invoiceEntity = getRepository().findById(id);
        if (invoiceEntity.isPresent()){
            return getConverter().convertToDto(invoiceEntity.get());
        }
        return null;
    }

    public InvoiceDto createInvoice(final InvoiceDto invoiceDto){
        final InvoiceEntity invoiceEntity = getRepository().save(getConverter().convertToEntity(invoiceDto));
        return getConverter().convertToDto(getRepository().save(invoiceEntity));
    }

//    public BookingDto attachInvoiceToBooking(final BookingDto bookingDto){
//        final InvoiceDto invoiceDto = createInvoice(prepareInvoice(bookingDto));
//        if (invoiceDto != null){
//            bookingDto.setInvoice(invoiceDto);
//        }
//        return bookingService.setInvoiceToBooking(bookingDto);
//    }

    private InvoiceDto prepareInvoice(final BookingDto bookingDto){

        final InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setId(bookingDto.getId());

        final int nightsCount = DatesCalculator.nightsCountCalculate(bookingDto.getCheckIn(),
                                                                        bookingDto.getCheckOut());

        final double totalPayment = PaymentCalculator.calculateTotalPayment(nightsCount, bookingDto.getRoomList());

        invoiceDto.setNightsCount(nightsCount);
        invoiceDto.setTotalPayment(totalPayment);

        return invoiceDto;
    }
}
