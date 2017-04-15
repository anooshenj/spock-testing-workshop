package de.assertagile.workshop.spocktesting;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(final User user) {
        if (null != this.userRepository.findUserByUserName(user.getUserName())) {
            throw new IllegalArgumentException("user already registered");
        }
        this.userRepository.saveUser(user.toEntity());
    }
}
