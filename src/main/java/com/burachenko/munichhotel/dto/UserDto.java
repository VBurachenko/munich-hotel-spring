package com.burachenko.munichhotel.dto;

import com.burachenko.munichhotel.enumeration.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto implements EntityDto{

    private Long userId;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String telNum;

    private LocalDate birthday;

    private Integer discount;

    private Boolean genderMale;

    private Integer blocking;

    private UserRole role;

    private Set<BookingDto> bookings = new HashSet<>();

    private Set<InvoiceDto> invoices = new HashSet<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(Boolean genderMale) {
        this.genderMale = genderMale;
    }

    public Integer getBlocking() {
        return blocking;
    }

    public void setBlocking(Integer blocking) {
        this.blocking = blocking;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDto> bookings) {
        this.bookings = bookings;
    }

    public Set<InvoiceDto> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<InvoiceDto> invoices) {
        this.invoices = invoices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(userId, userDto.userId) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(telNum, userDto.telNum) &&
                Objects.equals(birthday, userDto.birthday) &&
                Objects.equals(discount, userDto.discount) &&
                Objects.equals(genderMale, userDto.genderMale) &&
                Objects.equals(blocking, userDto.blocking) &&
                role == userDto.role &&
                Objects.equals(bookings, userDto.bookings) &&
                Objects.equals(invoices, userDto.invoices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, name, surname, telNum, birthday, discount, genderMale, blocking, role, bookings, invoices);
    }

}
