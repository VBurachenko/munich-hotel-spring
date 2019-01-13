package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractConverter<E extends AbstractEntity, DTO extends AbstractDto>{

    public abstract DTO convertToDto(final E entity);

    public abstract E convertToEntity(final DTO dto);

    public List<DTO> convertToDto(final Collection<E> entityCollection){
        if (entityCollection != null){
            List<DTO> list = new ArrayList<>();
            for (E e : entityCollection) {
                DTO convertToDto = convertToDto(e);
                list.add(convertToDto);
            }
            return list;
        }
        return null;
    }

    public List<E> convertToEntity(final Collection<DTO> dtoCollection){
        if (dtoCollection != null){
            List<E> list = new ArrayList<>();
            for (DTO dto : dtoCollection) {
                E convertToEntity = convertToEntity(dto);
                list.add(convertToEntity);
            }
            return list;
        }
        return null;
    }
}
