package com.pocker.planning.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pocker.planning.dto.UserStoryWithVotesDTO;
import com.pocker.planning.model.UserStory;
import com.pocker.planning.model.UserVote;
import com.pocker.planning.service.UserStoryService;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;

    @PostMapping("/add-story")
    public ResponseEntity<Long> addStory(@RequestParam String storyId,
                                        @RequestParam String description,
                                        @RequestParam Long sessionId) {
        UserStory userStory = userStoryService.addStory(storyId, description, sessionId);
        return ResponseEntity.ok(userStory.getId());
    }

    @DeleteMapping("/delete-story/{storyId}")
    public ResponseEntity<String> deleteStory(@PathVariable Long storyId) {
        userStoryService.deleteStory(storyId);
        return ResponseEntity.ok("User story deleted");
    }
    
    @PostMapping("/start-voting/{storyId}")
    public ResponseEntity<UserStory> startVoting(@PathVariable Long storyId) {
        UserStory userStory = userStoryService.startVoting(storyId);
        if (userStory != null) {
            return ResponseEntity.ok(userStory);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/vote-story/{storyId}/{userId}/{vote}")
    public ResponseEntity<String> voteStory(@PathVariable Long storyId,
                                            @PathVariable Long userId,
                                            @PathVariable int vote) {
        userStoryService.voteStory(storyId, userId, vote);
        return ResponseEntity.ok("Vote submitted");
    }

    @GetMapping("/get-votes/{storyId}")
    public UserStory getVotes(@PathVariable Long storyId) {
            return userStoryService.getVotes(storyId);
    }

    @GetMapping("/get-votes-new/{storyId}")
    public ResponseEntity<?> getVotesNew(@PathVariable Long storyId) {
        try {
            Optional<UserStoryWithVotesDTO> votes = userStoryService.getUserStoryWithVotes(storyId);
            if (votes.isPresent()) {
                return ResponseEntity.ok(votes.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
    
    @PostMapping("/finish-voting/{storyId}")
    public ResponseEntity<UserStory> finishVoting(@PathVariable Long storyId) {
        UserStory userStory = userStoryService.finishVoting(storyId);
        if (userStory != null) {
            return ResponseEntity.ok(userStory);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
