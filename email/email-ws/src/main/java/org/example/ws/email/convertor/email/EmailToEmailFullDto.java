package org.example.ws.email.convertor.email;

import org.example.model.db.events.h2.email.Email;
import org.example.ws.email.dto.email.EmailFullDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmailToEmailFullDto implements Converter<Email, EmailFullDto> {

    @Override
    public EmailFullDto convert(Email email) {
        EmailFullDto dto = new EmailFullDto();
        dto.setId(email.getId());
        dto.setEmail(email.getEmail());
        return dto;
    }
}
