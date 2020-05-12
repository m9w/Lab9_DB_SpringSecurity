package com.example.lab9.repos;

import com.example.lab9.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
