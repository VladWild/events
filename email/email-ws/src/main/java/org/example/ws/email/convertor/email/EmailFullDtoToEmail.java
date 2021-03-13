package org.example.ws.email.convertor.email;

import org.example.model.db.events.h2.email.Email;
import org.example.ws.email.dto.email.EmailFullDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmailFullDtoToEmail implements Converter<EmailFullDto, Email> {

    @Override
    public Email convert(EmailFullDto dto) {
        Email email = new Email();
        email.setId(dto.getId());
        email.setEmail(dto.getEmail());
        return email;
    }
}
