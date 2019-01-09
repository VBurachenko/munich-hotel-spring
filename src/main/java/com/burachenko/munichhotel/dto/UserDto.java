package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.enumeration.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserDto extends IdentifiableDto {

    private String email;

    private String password;

    private String name;

    private String surname;

    private String telNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Integer discount = 0;

    private Boolean genderMale = true;

    private Integer blocking = UserBlocking.NONE.ordinal();

    private UserRole role = UserRole.CUSTOMER;

    public boolean isPersisted(){
        return getId() != null;
    }
}
