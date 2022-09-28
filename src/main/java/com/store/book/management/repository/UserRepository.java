package com.store.book.management.repository;

import com.store.book.management.repository.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<Object> findByUsername(String username);
}