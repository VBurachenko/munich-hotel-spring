package com.burachenko.munichhotel.entity;

import com.burachenko.munichhotel.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name="tel_num", unique = true, nullable = false)
    private String telNum;

    @Column(name="blocking", nullable = false, columnDefinition = "TINYINT")
    private Integer blocking;

    @Column(name="role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

}
