package org.example.liquibaseexample.core.repository;

import org.example.liquibaseexample.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
