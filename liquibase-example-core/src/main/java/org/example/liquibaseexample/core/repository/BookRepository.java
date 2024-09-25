package org.example.liquibaseexample.core.repository;

import java.util.List;
import org.example.liquibaseexample.core.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByUserId(Long userId);
}
