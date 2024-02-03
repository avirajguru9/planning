package com.pocker.planning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pocker.planning.model.PokerSession;
import com.pocker.planning.service.PokerSessionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/sessions")
public class PokerSessionController {
    @Autowired
    private PokerSessionService pokerSessionService;
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping("new-session")
    public ResponseEntity<Long> createSession(@RequestParam String title, @RequestParam String deckType) {
        PokerSession pokerSession = pokerSessionService.createSession(title, deckType);
        return ResponseEntity.ok(pokerSession.getId());
    }

    @PostMapping("enter-session/{sessionId}")
    public ResponseEntity<String> enterSession(@PathVariable Long sessionId, @RequestParam String userName) {
        pokerSessionService.addUserToSession(sessionId, userName);
        // Get the request URL
        String requestUrl = request.getRequestURL().toString();
        return ResponseEntity.ok("Entered the session. Request URL: " + requestUrl);
    }

    @PostMapping("destroy-session/{sessionId}")
    public ResponseEntity<String> destroySession(@PathVariable Long sessionId) {
        pokerSessionService.destroySession(sessionId);
        return ResponseEntity.ok("Session destroyed");
    }
}
