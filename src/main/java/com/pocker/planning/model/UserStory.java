package com.pocker.planning.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user_story")
@Data
@NoArgsConstructor
public class UserStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storyId;
    private String description;
    private String status;

    @OneToMany(mappedBy = "userStory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserVote> votes = new ArrayList<>();

    private int emittedVotes;
}
