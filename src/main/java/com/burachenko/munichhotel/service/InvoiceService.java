package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.InvoiceConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.InvoiceDto;
import com.burachenko.munichhotel.dto.RoomDto;
import com.burachenko.munichhotel.entity.InvoiceEntity;
import com.burachenko.munichhotel.repository.InvoiceRepository;
import com.burachenko.munichhotel.service.util.DatesCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceConverter invoiceConverter;

    private final BookingService bookingService;


    public List<InvoiceEntity> getInvoicesList(){
        return invoiceRepository.findAll();
    }

    public InvoiceDto getInvoice(final Long id){
        final Optional<InvoiceEntity> invoiceEntity = invoiceRepository.findById(id);
        if (invoiceEntity.isPresent()){
            return invoiceConverter.convertToDto(invoiceEntity.get());
        }
        return null;
    }

    public InvoiceDto createInvoice(final InvoiceDto invoiceDto){
        final InvoiceEntity invoiceEntity = invoiceRepository.save(invoiceConverter.convertToEntity(invoiceDto));
        return invoiceConverter.convertToDto(invoiceRepository.save(invoiceEntity));
    }

    public BookingDto attachInvoiceToBooking(final BookingDto bookingDto){
        final InvoiceDto invoiceDto = createInvoice(prepareInvoice(bookingDto));
        if (invoiceDto != null){
            bookingDto.setInvoice(invoiceDto);
        }
        return bookingService.setInvoiceToBooking(bookingDto);
    }

    private InvoiceDto prepareInvoice(final BookingDto bookingDto){

        final InvoiceDto invoiceDto = new InvoiceDto();

        final int nightsCount = DatesCalculator.nightsCountCalculate(bookingDto.getCheckIn(),
                                                                        bookingDto.getCheckOut());

        final double totalPayment = calculateTotalPayment(nightsCount, bookingDto.getRoomSet());

        invoiceDto.setNightsCount(nightsCount);
        invoiceDto.setTotalPayment(totalPayment);

        return invoiceDto;
    }

    private double calculateTotalPayment(final int nightsCount, final Set<RoomDto> roomsInBooking){

        double commonDailyCost = 0.0;

        for (final RoomDto currentRoom : roomsInBooking){
            commonDailyCost += currentRoom.getPricePerNight();
        }
        return commonDailyCost * nightsCount;
    }
}
