package com.pocker.planning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocker.planning.model.UserStory;

public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}
