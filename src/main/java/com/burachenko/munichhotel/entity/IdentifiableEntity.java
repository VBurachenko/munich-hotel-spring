package com.burachenko.munichhotel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@Entity
@MappedSuperclass
public abstract class IdentifiableEntity {

    @Id
    @Column(name="id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long id;

}
