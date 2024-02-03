package com.pocker.planning.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pocker.planning.model.User;
import com.pocker.planning.model.UserStory;
import com.pocker.planning.model.UserVote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserStoryWithVotesDTO {
    
	private Long id;
    private String storyId;
    private String description;
    private String status;
    private int emittedVotes;
    private List<UserVote> votes;

    public UserStoryWithVotesDTO(Long id, String storyId, String description, String status, int emittedVotes, List<UserVote> votes) {
        this.id = id;
        this.storyId = storyId;
        this.description = description;
        this.status = status;
        this.emittedVotes = emittedVotes;
        this.votes = votes;
    }
}
