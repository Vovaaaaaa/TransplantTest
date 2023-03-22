package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public UserDto saveUser(final UserDto userDto) {
        if (userDto != null) {
            if (userRepository.existsById(Long.toString(userDto.getUserId()))) {
                throw new RuntimeException("User with id " + userDto.getUserId() + " already exists.");
            }
            logger.info("Saving user");
            final User user = UserDto.toUser(userDto);
            final User savedUser = userRepository.save(user);
            return UserDto.toDto(savedUser);
        }
        throw new RuntimeException("UserDto is null.");
    }

}
