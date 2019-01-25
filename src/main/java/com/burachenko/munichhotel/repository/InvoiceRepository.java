package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.InvoiceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends BasicRepository<InvoiceEntity> {

    List<InvoiceEntity> findByIdAndIsPayed(final Long id, final Boolean isPayed);

    List<InvoiceEntity> findByIdIn(final List<Long> ids);
}
