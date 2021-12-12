package de.assertagile.workshop.spocktesting;

import java.time.LocalDate;

public class User {

    private final String userName;
    private final String firstName;
    private final String lastName;
    private LocalDate birthday;

    public User(final String userName, final String firstName, final String lastName, LocalDate birthday) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        setBirthday(birthday);
    }

    public User(final String userName, final String firstName, final String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isOfAge() {
        if (this.birthday.isBefore(LocalDate.now().minusYears(18).plusDays(1)))
            return true;
        else return false;
    }

    public UserEntity toEntity() {
        return null;
    }

    public void setBirthday(LocalDate birthday) {
        if (birthday.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException();
        } else {
            this.birthday = birthday;
        }
    }
}
