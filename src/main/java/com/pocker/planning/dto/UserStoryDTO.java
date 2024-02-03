package com.pocker.planning.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserStoryDTO {
    private Long id;
    private String storyId;
    private String description;
    private String status;
    private int emittedVotes;
    private List<UserVoteDTO> votes;
}
