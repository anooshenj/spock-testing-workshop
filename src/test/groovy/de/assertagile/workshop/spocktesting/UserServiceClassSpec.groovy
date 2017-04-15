package de.assertagile.workshop.spocktesting

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class UserServiceClassSpec extends Specification {

    UserRepository userRepositoryMock = Mock()

    @Subject
    UserService userService = new UserService(userRepositoryMock)

    def "registerUser persists the user data via UserRepository (real User)"() {
        given:
        User user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(18))

        when:
        userService.registerUser(user)

        then:
        1 * userRepositoryMock.saveUser(user.toEntity())
    }

    def "registerUser persists the user data via UserRepository (Mock)"() {
        given:
        UserEntity userEntityMock = Mock(UserEntity)
        User userMock = Mock() { toEntity() >> userEntityMock }

        when:
        userService.registerUser(userMock)

        then:
        1 * userRepositoryMock.saveUser(userEntityMock)
    }

    def "registerUser does not persist user data if the user name was registered before"() {
        given:
        User user = new User("jdoe", "John", "Doe", LocalDate.now().minusYears(18))
        userRepositoryMock.findUserByUserName(user.userName) >> { user.toEntity() }

        when:
        userService.registerUser(user)

        then:
        0 * userRepositoryMock.saveUser(user.toEntity())

        and:
        thrown(IllegalArgumentException)
    }
}
