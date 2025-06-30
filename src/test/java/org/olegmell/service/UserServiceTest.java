package org.olegmell.service;
//Стандартный код без аннотаций Мок'а
/* import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.olegmell.domain.User;
import org.olegmell.repository.UserRepository;
import org.olegmell.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class); //Реализация поддельной БД
        userService = new UserService(userRepository); //Внедрение репозитория в сервис (тут userService использует Мок,а не реальный UserRepository)
    }

    @Test
    void testFindUserById() {
        User user = new User();
        user.setId(3); // Integer, не long
        user.setUsername("testuser");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1);
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
    }


    @Test
    void testFindByUsername() {
        User user = new User();
        user.setId(1);
        user.setUsername("Admin");

        when(userRepository.findByUsername("Admin")).thenReturn(user);

        User result = userService.findByUsername("Admin");

        assertNotNull(result);
        assertEquals("Admin", result.getUsername());
    }
} */

import org.olegmell.domain.User;
import org.olegmell.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private MailSender mailSender;


    @InjectMocks
    private UserService userService;

    @Test
    void ReturnUserById(){
        User user = new User();
        user.setId(1);
        user.setUsername("username");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1);
        assertTrue(result.isPresent());
        assertEquals(user.getId(),result.get().getId());

        verify(userRepository, times(1)).findById((1));
    }

    @Test
    void ReturnUserByUsername() {
        User user = new User();
        user.setUsername("Admin");

        when(userRepository.findByUsername("Admin")).thenReturn(user);

        User result = userService.findByUsername("Admin");

        assertNotNull(result);
        assertEquals("Admin", result.getUsername());
        verify(userRepository, times(1)).findByUsername("Admin");
    }

    @Test
    void addUser_shouldAddNewUserIfUsernameIsFree() {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("plainpassword");
        user.setEmail("email@example.com");

        when(userRepository.findByUsername("newuser")).thenReturn(null);

        PasswordEncoder encoder = mock(PasswordEncoder.class);
        when(encoder.encode("plainpassword")).thenReturn("encodedPassword");
        userService = new UserService(userRepository);
        ReflectionTestUtils.setField(userService, "passwordEncoder", encoder);

        MailSender mailSender = mock(MailSender.class);
        ReflectionTestUtils.setField(userService, "mailSender", mailSender);

        boolean result = userService.addUser(user);

        assertTrue(result);
        assertNotNull(user.getActivationCode());
        assertTrue(user.isActive());
        verify(userRepository, times(1)).save(user);
        verify(mailSender, times(1)).send(eq("email@example.com"), anyString(), contains("http://localhost:8080/activate/"));
    }

    @Test
    void addUser_shouldReturnFalseIfUsernameTaken() {
        User user = new User();
        user.setUsername("existinguser");

        when(userRepository.findByUsername("existinguser")).thenReturn(new User());

        boolean result = userService.addUser(user);

        assertFalse(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void activateUser_shouldActivateUserIfCodeIsCorrect() {
        User user = new User();
        user.setActivationCode("code123");

        when(userRepository.findByActivationCode("code123")).thenReturn(user);

        boolean result = userService.activateUser("code123");

        assertTrue(result);
        assertNull(user.getActivationCode());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void activateUser_shouldReturnFalseIfCodeNotFound() {
        when(userRepository.findByActivationCode("wrongcode")).thenReturn(null);

        boolean result = userService.activateUser("wrongcode");

        assertFalse(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void loadUserByUsername_shouldReturnUser() {
        User user = new User();
        user.setUsername("login");
        when(userRepository.findByUsername("login")).thenReturn(user);

        UserDetails result = userService.loadUserByUsername("login");
        assertNotNull(result);
        assertEquals("login", result.getUsername());
    }

    @Test
    void loadUserByUsername_shouldReturnFalseIfUsernameNotFound() {
        when(userRepository.findByUsername("username")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("username"));
    }

}