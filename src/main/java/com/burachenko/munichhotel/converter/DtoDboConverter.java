package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dbo.EntityDbo;
import com.burachenko.munichhotel.dto.EntityDto;

public interface DtoDboConverter <B extends EntityDbo, T extends EntityDto>{

    T convertToDto(final B dbo);

    B convertToDbo(final T dto);
}
