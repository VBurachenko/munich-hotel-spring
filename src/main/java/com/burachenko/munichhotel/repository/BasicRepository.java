package com.burachenko.munichhotel.repository;

import com.burachenko.munichhotel.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BasicRepository<Entity extends AbstractEntity> extends JpaRepository<Entity, Long> {

    List<Entity> findAllById(final long id);
}
