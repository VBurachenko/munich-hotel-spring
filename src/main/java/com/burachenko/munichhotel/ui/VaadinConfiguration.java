package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class VaadinConfiguration {

    @Bean
    @ViewScope
    public <DTO extends AbstractDto> Grid<DTO> getGrid(final DependencyDescriptor descriptor){
        final Class<?> dtoClass = descriptor.getResolvableType().getGeneric(0).resolve();

        Grid<DTO> grid = getConfiguredGrid(dtoClass);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ((MultiSelectionModel) grid.getSelectionModel())
                .setSelectAllCheckBoxVisibility(MultiSelectionModel.SelectAllCheckBoxVisibility.VISIBLE);
        grid.setSizeFull();
        return grid;
    }

    private <DTO extends AbstractDto> Grid<DTO> getConfiguredGrid(final Class<?> dtoClass){
        final Grid<DTO> grid = new Grid<>();
        final List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(dtoClass, fields::add);
        for (final Field field : fields){
            Column<DTO, ?> column = grid.addColumn(entity -> {
                try {
                    if (!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    return field.get(entity);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error to retrive prioperty value", e);
                }
            });
            setColumnCaption(column, field.getName());
        }
        return grid;
    }

    private <DTO> void setColumnCaption(final Column<DTO, ?> column, final String fieldName) {
        final String columnCaption = StringUtils.capitalize(StringUtils
                .join(StringUtils.splitByCharacterTypeCamelCase(fieldName), StringUtils.SPACE));
        column.setCaption(columnCaption);
    }

}
