package org.example.service.push;

import org.example.model.db.events.derby.push.Token;
import org.example.repositories.db.events.derby.push.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PushService {

    private final TokenRepository tokenRepository;

    public PushService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    public void saveToken(Token email) {
        tokenRepository.save(email);
    }
}
