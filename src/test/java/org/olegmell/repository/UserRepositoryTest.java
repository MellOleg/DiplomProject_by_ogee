package org.olegmell.repository;

import org.olegmell.domain.Role;
import org.olegmell.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("123");
        user.setEmail("test@example.com");
        user.setUser_role(Role.USER);
        user.setActive(false);

        userRepository.save(user);

        User found = userRepository.findByUsername("TestUser");

        assertNotNull(found);
        assertEquals("TestUser", found.getUsername());
        assertEquals("test@example.com", found.getEmail());
    }
}
