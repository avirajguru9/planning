package com.pocker.planning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocker.planning.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
