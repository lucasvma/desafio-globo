package com.globo.challenge.service;

import com.globo.challenge.dto.CepDto;
import com.globo.challenge.dto.UserDto;
import com.globo.challenge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static com.globo.challenge.FakeFactory.cep;
import static com.globo.challenge.FakeFactory.userDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @InjectMocks
    private UserService userServiceMock;

    @Mock
    private Clock clock;

    @Mock
    UserRepository userRepository;

    @Test
    void create() throws Exception {
        // given
        UserDto userDto = userDto();

        // when
        userServiceMock.create(userDto);

        // then
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void getViaCepData() throws Exception {
        // given
        CepDto expected = cep();

        // when
        CepDto actual = userService.getViaCepData("12630000");

        // then
        assertEquals(expected, actual);
    }

    @Test
    void checkAge() {
        // given
        int expected = 22;
        LocalDate birthday = LocalDate.of(1998, 7, 21);

        // when
        LocalDate date = LocalDate.now();
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        when(clock.instant()).thenReturn(instant);
        when(clock.getZone()).thenReturn(ZoneId.systemDefault());
        int actual = userService.checkAge(birthday);

        // then
        assertEquals(expected, actual);
    }
}