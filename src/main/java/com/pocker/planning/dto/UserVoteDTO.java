package com.pocker.planning.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVoteDTO {
    public UserVoteDTO(Long id2, Long id3, int vote2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
    private Long userId; // Assuming you want to expose the user ID
    private int vote;
}
