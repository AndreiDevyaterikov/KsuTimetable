package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.User;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.UserRepository;
import ksutimetable.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> {
                    var message = String.format(Constants.NOT_FOUND_USER_WITH_ID, userId);
                    log.info(message);
                    return new KsuTimetableException(message, 404);
                });
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
