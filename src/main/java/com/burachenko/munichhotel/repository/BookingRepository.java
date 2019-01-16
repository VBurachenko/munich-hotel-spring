package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.enumeration.BookingStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends BasicRepository<BookingEntity> {

    @Query(value = "SELECT * FROM `booking` WHERE `id`=:id OR `user_id`=:id OR `invoice_id`=:id", nativeQuery = true)
    List<BookingEntity> findAllByBookingIdOrUserIdOrInvoiceId(final long id);

    List<BookingEntity> getBookingListByUserId(final long userId);

    Optional<BookingEntity> getBookingByInvoiceId(final long invoiceId);

    List<BookingEntity> findAllByCheckInBeforeAndCheckOutAfter(LocalDate before, LocalDate after);

    List<BookingEntity> getByCheckInBetweenAndStatus(final LocalDate checkIn, final LocalDate checkIn2, final BookingStatus status);

    List<BookingEntity> getByCheckInBetween(LocalDate from, LocalDate to);
}
