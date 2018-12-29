package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.impl.BookingConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.SearchUnitDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import com.burachenko.munichhotel.repository.BookingRepository;
import com.burachenko.munichhotel.service.util.IdCreator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingConverter bookingConverter;

    private final UserService userService;

    public List<BookingEntity> getBookingsList(){
        return bookingRepository.findAll();
    }

    public BookingDto createBooking(final BookingDto bookingDto){
        int startIndex = 1;
        long bookingId = IdCreator.createLongIdByTodayDate(1);
        while (getBooking(bookingId) != null){
            bookingId = IdCreator.createLongIdByTodayDate(++startIndex);
        }
        bookingDto.setId(bookingId);
        final BookingEntity bookingEntity = bookingRepository.save(
                                                    bookingConverter.convertToEntity(bookingDto));
        if (bookingEntity != null){
            return bookingConverter.convertToDto(bookingEntity);
        }
        return null;
    }

    public BookingDto prepareBookingToOrder(final SearchUnitDto searchUnit, final long userId){
        final BookingDto preparedBooking = new BookingDto();
        preparedBooking.setCheckIn(searchUnit.getCheckIn());
        preparedBooking.setCheckOut(searchUnit.getCheckOut());
        preparedBooking.setAdultCount(searchUnit.getAdultCount());
        preparedBooking.setChildCount(searchUnit.getChildCount());
        preparedBooking.setUser(userService.getUser(userId));
        return preparedBooking;
    }

    public BookingDto getBooking(final long id){
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()){
            return bookingConverter.convertToDto(bookingEntity.get());
        }
        return null;
    }

    public BookingDto updateBooking(final BookingDto bookingDto, final long id){
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()){
            bookingDto.setId(id);
            return bookingConverter.convertToDto(bookingEntity.get());
        }
         return null;
    }

    public BookingDto changeBookingStatus(final long id, final BookingStatus status){
        final BookingDto bookingDto = getBooking(id);
        if (bookingDto != null){
            bookingDto.setStatus(status);
            return updateBooking(bookingDto, id);
        }
        return null;
    }

    BookingDto setInvoiceToBooking(final BookingDto bookingDto){
        return updateBooking(bookingDto, bookingDto.getId());
    }

    public boolean deleteBooking(final long id) {
        final Optional<BookingEntity> bookingEntity = bookingRepository.findById(id);
        if (bookingEntity.isPresent()) {
            bookingRepository.deleteById(id);
        }
        return !bookingRepository.findById(id).isPresent();
    }

    public BookingDto getBookingByInvoiceId(final long invoiceId){
        final Optional<BookingEntity> bookingEntity = bookingRepository.getBookingByInvoiceId(invoiceId);
        if (bookingEntity.isPresent()){
            return bookingConverter.convertToDto(bookingEntity.get());
        }
        return null;
    }

    public Set<BookingDto> getBookingListByUserId(final long userId){
        return bookingConverter.convertToDto(bookingRepository.getBookingSetByUserId(userId));
    }
}
