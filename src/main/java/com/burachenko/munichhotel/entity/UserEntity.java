package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class UserEntity extends IdentifiableEntity {

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name="tel_num", unique = true, nullable = false)
    private String telNum;

    @Column(name = "birthday")
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate birthday;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "gender_male")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean genderMale;

    @Column(name="blocking", nullable = false, columnDefinition = "TINYINT")
    private Integer blocking;

    @Column(name="role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

}
