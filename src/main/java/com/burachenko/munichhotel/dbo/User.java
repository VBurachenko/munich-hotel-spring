package com.burachenko.munichhotel.dbo;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements EntityDbo {

    private static final long serialVersionUID = 7327946027644103279L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id", unique = true, nullable = false, updatable = false, insertable = false)
    private Long userId;

    @Column(name="email", unique = true, nullable = false, length = 60)
    private String email;

    @Column(name="password", nullable = false, length = 32)
    private String password;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="surname", nullable = false, length = 100)
    private String surname;

    @Column(name="telNum", unique = true, nullable = false, length = 20)
    private String telNum;

    @Column(name="birthday", nullable = false)
    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate birthday;

    @Column(name="discount", nullable = false, precision = 2)
    private Integer discount = 0;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name="gender_male", nullable = false, precision = 1, columnDefinition = "TINYINT")
    private Boolean genderMale;

    @Column(name="blocking", nullable = false, precision = 1, columnDefinition = "TINYINT")
    private Integer blocking = 0;

    @Column(name="role", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private UserRole role = UserRole.CUSTOMER;

    @OneToMany(mappedBy = "user")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Invoice> invoices = new HashSet<>();

    public User() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(telNum, user.telNum) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(discount, user.discount) &&
                Objects.equals(genderMale, user.genderMale) &&
                Objects.equals(blocking, user.blocking) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, name, surname, telNum, birthday, discount, genderMale, blocking, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telNum='" + telNum + '\'' +
                ", birthday=" + birthday +
                ", discount=" + discount +
                ", genderMale=" + genderMale +
                ", blocking=" + blocking +
                ", role=" + role +
                '}';
    }
}
