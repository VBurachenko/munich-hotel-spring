package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.converter.UserConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.repository.UserRepository;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.data.sort.SortDirection;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService extends AbstractService<UserDto, UserEntity, UserRepository>{

    public UserService(final UserRepository repository, final UserConverter userConverter) {
        super(repository, userConverter);
    }

    @Override
    protected boolean beforeSave(UserDto userDto) {
        final Optional<UserEntity> userByEmail = getRepository().findUserByEmail(userDto.getEmail());
        final Optional<UserEntity> userByTelNum = getRepository().findByTelNum(userDto.getTelNum());
        return !userByEmail.isPresent() && !userByTelNum.isPresent();
    }

    public List<UserDto> getUserList(){
        final List<UserDto> dtoUserList = new ArrayList<>();
        for (final UserEntity user : getRepository().findAll()) {
            final UserDto userDto = getConverter().convertToDto(user);
            dtoUserList.add(userDto);
        }
        return dtoUserList;
    }

    public List<UserDto> getUserList(final String filterString){
        if (!filterString.isEmpty()){
            return getConverter().convertToDto(getRepository().findUserByEmailOrTelNum(filterString));
        }
        return getUserList();
    }

    public Stream<UserDto> getUserList(final Query<UserDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        final List<UserDto> userDtoList = getRepository().findAll(pageRequest).getContent().stream().map(getConverter()::convertToDto).collect(Collectors.toList());
        return userDtoList.stream();
    }

    private PageRequest preparePageRequest(final Query<UserDto, String> query) {
        final int page = query.getOffset() / query.getLimit();

        final List<Sort.Order> sortOrders = query.getSortOrders().stream()
                .map(sortOrder -> new Sort.Order(sortOrder.getDirection() == SortDirection.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, sortOrder.getSorted())).collect(Collectors.toList());
        return PageRequest.of(page, query.getLimit(), sortOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortOrders));
    }

}
