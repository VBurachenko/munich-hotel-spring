package com.burachenko.munichhotel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class UserAccountEntity extends IdentifiableEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate birthday;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "gender_male")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean genderMale;
}
