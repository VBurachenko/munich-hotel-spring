package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dbo.EntityDbo;
import com.burachenko.munichhotel.dto.EntityDto;

import java.util.HashSet;
import java.util.Set;

public interface DtoDboConverter <B extends EntityDbo, T extends EntityDto>{

    T convertToDto(final B dbo);

    B convertToDbo(final T dto);

    default Set<T> convertToDto(final Set<B> dboSet){
        if (dboSet != null){
            final Set<T> dtoSet = new HashSet<>();
            for (final B dbo : dboSet){
                final T dto = convertToDto(dbo);
                dtoSet.add(dto);
            }
            return dtoSet;
        }
        return null;
    }

    default Set<B> convertToDbo(final Set<T> dtoSet){
        if (dtoSet != null){
            final Set<B> dboSet = new HashSet<>();
            for (final T dto : dtoSet){
                final B dbo = convertToDbo(dto);
                dboSet.add(dbo);
            }
            return dboSet;
        }
        return null;
    }
}
