package org.example.liquibaseexample.core.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.example.liquibaseexample.core.BaseRepositoryTest;
import org.example.liquibaseexample.core.domain.Book;
import org.example.liquibaseexample.core.domain.User;
import org.example.liquibaseexample.core.domain.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private BookRepository repository;

    private User user1;
    private User user1afterSave;
    private User user2;
    private User user2afterSave;
    private Book book1;
    private Book book2;

    public static User createUser(String email, String password, String name, UserType userType) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUserType(userType);
        return user;
    }

    public static List<String> createAuthors(String ... authors) {
        return Arrays.asList(authors);
    }

    public static Book createBook(String name, List<String> authors, User user) {
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);
        book.setUser(user);
        return book;
    }

    @BeforeEach
    public void setUp() {
        user1 = createUser("johndoe@gmail.com", "hashpass", "John Doe", UserType.USER);
        user2 = createUser("sararay@gmail.com", "abrakadabra", "Sara Ray", UserType.USER);
        book1 = createBook("Непрерывное развитие API", createAuthors("Меджуи", "Уайлд", "Митра"), null);
        book2 = createBook("Чистая архитектура", createAuthors("Мартин"), null);
    }

    public void persistBooks() {
        user1afterSave = testEntityManager.persist(user1);
        user2afterSave = testEntityManager.persist(user2);
        book1.setUser(user1afterSave);
        testEntityManager.persist(book1);
        book2.setUser(user2afterSave);
        testEntityManager.persist(book2);
    }

    @Test
    public void should_find_no_books_if_repository_is_empty() {
        List<Book> users = repository.findAll();

        assertThat(users).isEmpty();
    }

    @Test
    public void should_store_a_book() {
        User user1afterSave = testEntityManager.persist(user1);
        book1.setUser(user1afterSave);
        Book bookAfterSave = repository.save(book1);

        assertThat(bookAfterSave).isEqualTo(book1);
    }

    @Test
    public void should_delete_all_books() {
        persistBooks();

        repository.deleteAll();

        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void should_find_all_books() {
        persistBooks();

        Iterable<Book> users = repository.findAll();

        assertThat(users).hasSize(2).contains(book1, book2);
    }

    @Test
    public void should_find_book_by_id() {
        persistBooks();

        Optional<Book> foundBook = repository.findById(book2.getId());

        if (foundBook.isPresent()) {
            assertThat(foundBook.get()).isEqualTo(book2);
        } else {
            fail("Book2 not found");
        }
    }
}
