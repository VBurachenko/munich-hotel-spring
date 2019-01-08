package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.IdentifiableDto;
import com.burachenko.munichhotel.entity.IdentifiableEntity;

import java.util.ArrayList;
import java.util.List;

public interface EntityDtoConverter<E extends IdentifiableEntity, DTO extends IdentifiableDto>{

    DTO convertToDto(final E entity);

    E convertToEntity(final DTO dto);

    default List<DTO> convertToDto(final List<E> entityList){
        if (entityList != null){
            final List<DTO> dtoList = new ArrayList<>();
            for (final E entity : entityList){
                final DTO dto = convertToDto(entity);
                dtoList.add(dto);
            }
            return dtoList;
        }
        return null;
    }

    default List<E> convertToEntity(final List<DTO> dtoList){
        if (dtoList != null){
            final List<E> entityList = new ArrayList<>();
            for (final DTO dto : dtoList){
                final E entity = convertToEntity(dto);
                entityList.add(entity);
            }
            return entityList;
        }
        return null;
    }
}
