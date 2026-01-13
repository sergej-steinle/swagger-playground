package com.steinledevops.swaggerplayground.repository;

import com.steinledevops.swaggerplayground.entity.Todo;
import com.steinledevops.swaggerplayground.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOwner(User owner);
    Optional<Todo> findByIdAndOwner(Long id, User owner);
}
