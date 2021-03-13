package org.example.ws.push.convertor.token;

import org.example.model.db.events.derby.push.Token;
import org.example.ws.push.dto.token.TokenFullDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenToTokenFullDto implements Converter<Token, TokenFullDto> {

    @Override
    public TokenFullDto convert(Token token) {
        TokenFullDto dto = new TokenFullDto();
        dto.setId(token.getId());
        dto.setToken(token.getToken());
        return dto;
    }
}
