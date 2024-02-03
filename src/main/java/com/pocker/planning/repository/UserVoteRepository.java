package com.pocker.planning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocker.planning.model.UserStory;
import com.pocker.planning.model.UserVote;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {

	List<UserVote> findByUserStory(UserStory userStory);
}
