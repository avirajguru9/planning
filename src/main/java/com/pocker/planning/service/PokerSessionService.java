package com.pocker.planning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pocker.planning.model.PokerSession;
import com.pocker.planning.model.User;
import com.pocker.planning.repository.PokerSessionRepository;
import com.pocker.planning.repository.UserRepository;

@Service
public class PokerSessionService {
    @Autowired
    private PokerSessionRepository pokerSessionRepository;

    @Autowired
    private UserRepository userRepository;

    public PokerSession createSession(String title, String deckType) {
        PokerSession pokerSession = new PokerSession();
        pokerSession.setTitle(title);
        pokerSession.setDeckType(deckType);
        return pokerSessionRepository.save(pokerSession);
    }

    public PokerSession findById(Long id) {
        return pokerSessionRepository.findById(id).orElse(null);
    }

    public void destroySession(Long sessionId) {
        pokerSessionRepository.deleteById(sessionId);
        // Additional cleanup if needed
    }

    public void addUserToSession(Long sessionId, String userName) {
        PokerSession pokerSession = pokerSessionRepository.findById(sessionId).orElse(null);
        if (pokerSession != null) {
            User user = new User();
            user.setName(userName);
            userRepository.save(user);
            pokerSession.getMembers().add(user);
            pokerSessionRepository.save(pokerSession);
        }
    }
}
