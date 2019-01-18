package com.burachenko.munichhotel.ui;

import com.burachenko.munichhotel.dto.AbstractDto;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaadinConfiguration {

    @Bean
    @ViewScope
    public <DTO extends AbstractDto> Grid<DTO> getGrid(final DependencyDescriptor descriptor){
        final Class<?> dtoClass = descriptor.getResolvableType().getGeneric(0).resolve();
        Grid<DTO> grid = new Grid<DTO>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        ((MultiSelectionModel) grid.getSelectionModel())
                .setSelectAllCheckBoxVisibility(MultiSelectionModel.SelectAllCheckBoxVisibility.VISIBLE);
        grid.setSizeFull();
        return grid;
    }
}
