package de.assertagile.workshop.spocktesting

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

        expect:
        !user.isOfAge()
    }

    def "isOfAge should return true if the user's birthday is 18 years ago"() {
        given:
        user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(18))

        expect:
        user.isOfAge()
    }

    @Unroll("creating a user with a future birthday #birthDay throws an IllegalArgumentExpecption")
    def "creating a user with a future birthday throws an IllegalArgumentExpecption"(LocalDate birthDay) {
        when:
        new User("jdoe", "John", "Doe", birthDay)

        then:
        thrown(IllegalArgumentException)

        where:
        birthDay << [LocalDate.now().plusDays(1), LocalDate.now().plusMonths(1), LocalDate.now().plusYears(1)]
    }

    def "no exception in thrown if the birthday is in the past"() {
        when:
        new User("jdoe", "John", "Doe", LocalDate.now())

        then:
        notThrown(Exception)
    }

    @Unroll("isOfAge should return true if the user's birthday is #birthDay")
    def "isOfAge should return true if the user's birthday is more than 18 years ago"(LocalDate birthDay) {
        given:
        user = new User("jdoe", "John", "Doe", birthDay)

        expect:
        user.isOfAge()

        where:
        birthDay << [LocalDate.now().minusYears(18), LocalDate.now().minusYears(35)]
    }

    @Unroll("isOfAge should return false if the user's birthday is #birthDay")
    def "isOfAge should return false if the user's birthday is less than 18 years ago"(LocalDate birthDay) {
        given:
        user = new User("jdoe", "John", "Doe", birthDay)

        expect:
        !user.isOfAge()

        where:
        birthDay << [LocalDate.now().minusYears(18).plusDays(1), LocalDate.now().minusYears(10)]
    }
}
