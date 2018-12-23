package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private Integer blocking = 0;

    private UserRole role = UserRole.CUSTOMER;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BookingDto> bookingSet = new HashSet<>();
}
