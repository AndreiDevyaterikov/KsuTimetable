package ksutimetable.services.impl;

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
    public void saveUsers(List<User> users) {
        userRepository.saveAll(users);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        var userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            return userOpt.get();
        } else {
            var message = String.format("Not found user with id %s", userId);
            log.info(message);
            throw new KsuTimetableException(message, 404);
        }
    }
}
