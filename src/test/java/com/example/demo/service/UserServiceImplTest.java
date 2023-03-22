package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUserTest_UserDtoIsNull() {
        final Exception actualException = assertThrows(RuntimeException.class, () -> userService.saveUser(null));
        assertEquals("UserDto is null.", actualException.getMessage());
    }

    @Test
    public void saveUserTest_UserAlreadyExistsWithSameId() {
        final UserDto userDto = UserDto.builder()
                .userId(123)
                .userName("Test")
                .userAge(11)
                .build();
        when(userRepositoryMock.existsById(any())).thenReturn(true);

        final Exception actualException = assertThrows(RuntimeException.class, () -> userService.saveUser(userDto));
        assertEquals("User with id 123 already exists.", actualException.getMessage());
    }

    @Test
    public void saveUserTest_CorrectSaving() {
        final UserDto userDto = UserDto.builder()
                .userId(123)
                .userName("Test")
                .userAge(11)
                .build();
        when(userRepositoryMock.save(any())).thenReturn(UserDto.toUser(userDto));

        final UserDto actualUserDto = userService.saveUser(userDto);
        assertEquals(userDto, actualUserDto);
    }

}