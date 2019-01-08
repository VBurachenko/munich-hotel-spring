package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDto extends IdentifiableDto {

    private String email;

    private String password;

    private String telNum;

    private Integer blocking = 0;

    private UserRole role = UserRole.CUSTOMER;
}
