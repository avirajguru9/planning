package com.pocker.planning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pocker.planning.model.PokerSession;

public interface PokerSessionRepository extends JpaRepository<PokerSession, Long> {
}
