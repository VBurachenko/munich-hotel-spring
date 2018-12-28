package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.IdentifiableDto;
import com.burachenko.munichhotel.entity.IdentifiableEntity;

import java.util.HashSet;
import java.util.Set;

public interface EntityDtoConverter<E extends IdentifiableEntity, DTO extends IdentifiableDto>{

    DTO convertToDto(final E entity);

    E convertToEntity(final DTO dto);

    default Set<DTO> convertToDto(final Set<E> entitySet){
        if (entitySet != null){
            final Set<DTO> dtoSet = new HashSet<>();
            for (final E entity : entitySet){
                final DTO dto = convertToDto(entity);
                dtoSet.add(dto);
            }
            return dtoSet;
        }
        return null;
    }

    default Set<E> convertToEntity(final Set<DTO> dtoSet){
        if (dtoSet != null){
            final Set<E> entitySet = new HashSet<>();
            for (final DTO dto : dtoSet){
                final E entity = convertToEntity(dto);
                entitySet.add(entity);
            }
            return entitySet;
        }
        return null;
    }
}
