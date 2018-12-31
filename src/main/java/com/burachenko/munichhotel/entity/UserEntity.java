package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="tel_num", unique = true, nullable = false)
    private String telNum;

    @Column(name="birthday", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate birthday;

    @Column(name="discount", nullable = false)
    private Integer discount;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name="gender_male", nullable = false, columnDefinition = "TINYINT")
    private Boolean genderMale;

    @Column(name="blocking", nullable = false, columnDefinition = "TINYINT")
    private Integer blocking;

    @Column(name="role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @EqualsAndHashCode.Exclude
    private Set<BookingEntity> bookingSet = new HashSet<>();
}
