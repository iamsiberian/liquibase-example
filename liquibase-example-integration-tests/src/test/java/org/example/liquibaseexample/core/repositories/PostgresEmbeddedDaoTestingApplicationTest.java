package org.example.liquibaseexample.core.repositories;

import static org.example.liquibaseexample.core.repositories.Utils.createAuthors;
import static org.example.liquibaseexample.core.repositories.Utils.createBook;
import static org.example.liquibaseexample.core.repositories.Utils.createUser;
import static org.junit.Assert.assertNotNull;

import java.time.Duration;
import org.example.liquibaseexample.core.configuration.DbConfig;
import org.example.liquibaseexample.core.domain.Book;
import org.example.liquibaseexample.core.domain.User;
import org.example.liquibaseexample.core.domain.UserType;
import org.example.liquibaseexample.core.repository.BookRepository;
import org.example.liquibaseexample.core.repository.UserRepository;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DbConfig.class })
@ActiveProfiles("DaoTest")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:dao/InsertUsers.sql")
public class PostgresEmbeddedDaoTestingApplicationTest {
    private final static long USER_ID_FROM_INIT_SQL = 13L;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
        "postgres:10.3")
        .withDatabaseName("test")
        .withUsername("user")
        .withPassword("pass")
        .withStartupTimeout(Duration.ofSeconds(600)
        );

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    private User user1;
    private User user1afterSave;
    private User user2;
    private User user2afterSave;
    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        user1 = createUser("johndoe@gmail.com", "hashpass", "John Doe", UserType.USER);
        user2 = createUser("sararay@gmail.com", "abrakadabra", "Sara Ray", UserType.ADMIN);
        book1 = createBook("Непрерывное развитие API", createAuthors("Меджуи", "Уайлд", "Митра"), null);
        book2 = createBook("Чистая архитектура", createAuthors("Мартин"), null);
        user1afterSave = userRepository.save(user1);
        user2afterSave = userRepository.save(user2);
        book1.setUser(user1afterSave);
        book2.setUser(user2afterSave);
    }

    @Test
    @Transactional
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void test_save_book() {
        bookRepository.save(book1);
        assertNotNull(bookRepository.findAllByUserId(user1.getId()));
        assertNotNull(bookRepository.findAllByUserId(USER_ID_FROM_INIT_SQL));
    }
}
