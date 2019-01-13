package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.AbstractConverter;
import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.entity.AbstractEntity;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public abstract class AbstractService<DTO extends AbstractDto, Entity extends AbstractEntity, Repository extends JpaRepository> {

    private Repository repository;

    private AbstractConverter<Entity, DTO> converter;

    protected abstract boolean beforeSave(DTO dto);

    public DTO findById(final long id){
        final Optional<Entity> abstractEntity = repository.findById(id);
        return abstractEntity.map(entity -> converter.convertToDto(entity)).orElse(null);
    }

    public List<DTO> findAll(){
        return converter.convertToDto(repository.findAll());
    }

    public Stream<DTO> findWithPagination(final Query<DTO, String> query){
        return null;
    }

    public boolean deleteAll(final Collection<DTO> toDelete){
        repository.deleteAll(converter.convertToEntity(toDelete));
        for (DTO dto : toDelete) {
            if (findById(dto.getId()) != null) {
                return false;
            }
        }
        return true;
    }

    public long count(){
        return repository.count();
    }

    @Transactional
    public DTO save (final DTO toSaveDto){
        if (beforeSave(toSaveDto)){
            final Entity abstractEntity = (Entity) repository.save(converter.convertToEntity(toSaveDto));
            return converter.convertToDto(abstractEntity);
        }
        return null;
    }

    public Repository getRepository() {
        return repository;
    }

    public AbstractConverter<Entity, DTO> getConverter() {
        return converter;
    }

}
