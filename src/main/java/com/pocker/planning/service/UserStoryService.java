package com.pocker.planning.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocker.planning.dto.UserStoryWithVotesDTO;
import com.pocker.planning.model.User;
import com.pocker.planning.model.UserStory;
import com.pocker.planning.model.UserVote;
import com.pocker.planning.repository.UserStoryRepository;
import com.pocker.planning.repository.UserVoteRepository;

@Service
public class UserStoryService {
    @Autowired
    private UserStoryRepository userStoryRepository;
    
    @Autowired
    private UserVoteRepository userVoteRepository;

    public UserStory addStory(String storyId, String description, Long sessionId) {
        UserStory userStory = new UserStory();
        userStory.setStoryId(storyId);
        userStory.setDescription(description);
        userStory.setStatus("PENDING");

        // Additional logic, if needed (associating with a session, etc.)

        return userStoryRepository.save(userStory);
    }

    public void deleteStory(Long storyId) {
        UserStory userStory = userStoryRepository.findById(storyId).orElse(null);

        if (userStory != null && "PENDING".equals(userStory.getStatus())) {
            userStoryRepository.deleteById(storyId);
        }
    }
    
    public UserStory startVoting(Long storyId) {
        UserStory userStory = userStoryRepository.findById(storyId).orElse(null);

        if (userStory != null && ("PENDING".equals(userStory.getStatus()) || "VOTED".equals(userStory.getStatus()))) {
            userStory.setStatus("VOTING");
            return userStoryRepository.save(userStory);
        }

        return null;
    }

    public void voteStory(Long storyId, Long userId, int vote) {
        UserStory userStory = userStoryRepository.findById(storyId).orElse(null);

        if (userStory != null && "VOTING".equals(userStory.getStatus())) {
            UserVote userVote = new UserVote();
            userVote.setUser(new User(userId));
            userVote.setVote(vote);
            userVote.setUserStory(userStory);
            userVoteRepository.save(userVote);

            userStory.getVotes().add(userVote);
            userStory.setEmittedVotes(userStory.getEmittedVotes() + 1);
            userStoryRepository.save(userStory);
        }
    }

    public UserStory getVotes(Long storyId) {
        return userStoryRepository.findById(storyId).orElse(null);
//        return userVoteRepository.findByUserStory(userStory);
//        if (userStory != null) {
//            return userStory.getVotes();
//        }
//
//        return Collections.emptyList();
    }
    
    public Optional<UserStoryWithVotesDTO> getUserStoryWithVotes(Long storyId) {
        return userStoryRepository.findById(storyId)
                .map(userStory -> {
                    List<UserVote> userVotes = userVoteRepository.findByUserStory(userStory);
                    return new UserStoryWithVotesDTO(
                            userStory.getId(),
                            userStory.getStoryId(),
                            userStory.getDescription(),
                            userStory.getStatus(),
                            userStory.getEmittedVotes(),
                            userVotes
                    );
                });
    }

    public UserStory finishVoting(Long storyId) {
        UserStory userStory = userStoryRepository.findById(storyId).orElse(null);

        if (userStory != null && "VOTING".equals(userStory.getStatus())) {
            userStory.setStatus("VOTED");
            return userStoryRepository.save(userStory);
        }

        return null;
    }
}