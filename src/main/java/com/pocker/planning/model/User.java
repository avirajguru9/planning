package com.pocker.planning.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user")
@Data
@NoArgsConstructor
public class User {    

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Constructors, getters, setters
    @ManyToMany(mappedBy = "members")
    private List<PokerSession> sessions = new ArrayList<>();
    
    public User(Long id) {
        this.id = id;
    }
}
