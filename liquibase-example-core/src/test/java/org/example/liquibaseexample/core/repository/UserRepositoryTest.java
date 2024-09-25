package org.example.liquibaseexample.core.repository;

import org.example.liquibaseexample.core.BaseRepositoryTest;
import org.example.liquibaseexample.core.domain.User;
import org.example.liquibaseexample.core.domain.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test_save_user_with_large_usertype() {
        User savedUser = userRepository.save(
                new User(null, "minyukhin.ilya@gmail.com", "123", "Minyukhin Ilya", UserType.USER)
        );

        System.out.println("savedUser is: " + savedUser);
    }
}
