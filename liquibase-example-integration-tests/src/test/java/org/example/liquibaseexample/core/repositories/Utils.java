package org.example.liquibaseexample.core.repositories;

import java.util.Arrays;
import java.util.List;
import org.example.liquibaseexample.core.domain.Book;
import org.example.liquibaseexample.core.domain.User;
import org.example.liquibaseexample.core.domain.UserType;

public class Utils {
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
}
