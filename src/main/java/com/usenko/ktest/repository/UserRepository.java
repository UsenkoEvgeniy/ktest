package com.usenko.ktest.repository;

import com.usenko.ktest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
