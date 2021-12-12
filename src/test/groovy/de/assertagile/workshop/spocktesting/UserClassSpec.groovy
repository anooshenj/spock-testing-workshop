package de.assertagile.workshop.spocktesting

import spock.lang.PendingFeature
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate

class UserClassSpec extends Specification {

    @Subject
    User user

    def "isOfAge should return false if the user's birthday is less than 18 years ago"() {
        given:
        user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(17))

        when:
        boolean isOfAge = user.isOfAge()

        then:
        !isOfAge
    }

    def "isOfAge should return true if the user's birthday is 18 years ago"() {
        given:
        user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(18))

        expect:
        user.isOfAge()
    }

    @Unroll
    def "isOfAge should return true if the user's birthday is more than 18 years ago. Age #age is greater than 18"() {
        given:
        user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(age as long))

        expect:
        user.isOfAge()

        where:
        age << ([23, 60, 18])
    }

    @Unroll
    def "isOfAge should return true if the user's birthday is more than 18 years ago. Age #age is less than 18"() {
        given:
        user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(age as long))

        expect:
        !user.isOfAge()

        where:
        age << ([14, 16, 12])
    }



    def "creating a user with a future birthday throws an IllegalArgumentException"() {
        given:
        user = new User("jdoe", "John", "Doe")

        when:
        user.setBirthday(LocalDate.now().plusYears(1))

        then:
        thrown IllegalArgumentException
    }

    def "creating a user with a future birthday throws an IllegalArgumentException if the birthday is given in constructor"() {
        when:
        user = new User("jdoe", "John", "Doe", LocalDate.now().plusYears(1))

        then:
        thrown IllegalArgumentException
    }

    def "no exception in thrown if the birthday is in the past"() {
        given:
        user = new User("jdoe", "John", "Doe")

        when:
        user.setBirthday(LocalDate.now().minusYears(18))

        then:
        notThrown IllegalArgumentException
    }
}
