package org.example.ws.push.convertor.token;

import org.example.model.db.events.derby.push.Token;
import org.example.ws.push.dto.token.TokenDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenDtoToToken implements Converter<TokenDto, Token> {

    @Override
    public Token convert(TokenDto dto) {
        Token token = new Token();
        token.setToken(dto.getToken());
        return token;
    }
}
