package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.AbstractConverter;
import com.burachenko.munichhotel.dto.AbstractDto;
import com.burachenko.munichhotel.entity.AbstractEntity;
import com.burachenko.munichhotel.repository.BasicRepository;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public abstract class AbstractService<DTO extends AbstractDto, Entity extends AbstractEntity, Repository extends BasicRepository> {

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

    private PageRequest preparePageRequest(final Query<DTO, String> query){
        final int pageNumber = query.getOffset() / query.getLimit();
        final List<Sort.Order> sortOrders = new ArrayList<>();
        for (QuerySortOrder sortOrder : query.getSortOrders()){
            Sort.Order order = new Sort.Order(sortOrder.getDirection() == SortDirection.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, sortOrder.getSorted());
            sortOrders.add(order);
        }
        return PageRequest.of(pageNumber, query.getLimit(), sortOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortOrders));
    }

    public Stream<DTO> findByFilterQueryWithPagination(final Query<DTO, String> query){
        final PageRequest pageRequest = preparePageRequest(query);
        final Optional <String> filterParameter = query.getFilter();
        List<DTO> dtoList = null;
        if (query.getFilter().isPresent() && !query.getFilter().get().isEmpty()){
            dtoList = findByFilterParameter(filterParameter.get());
        } else {
            dtoList = converter.convertToDto(repository.findAll(pageRequest).getContent());
        }
        return dtoList.stream();
    }

    public abstract List<DTO> findByFilterParameter(final String filterParameter);

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
