package de.assertagile.workshop.spocktesting;

import java.time.LocalDate;
import java.util.Objects;

class UserEntity {

    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public UserEntity() {}

    public UserEntity(String userName, String firstName, String lastName, LocalDate birthday) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserEntity that = (UserEntity) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName, birthday);
    }
}
