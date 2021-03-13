package org.example.ws.email.convertor.email;

import org.example.model.db.events.h2.email.Email;
import org.example.ws.email.dto.email.EmailDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmailDtoToEmail implements Converter<EmailDto, Email> {

    @Override
    public Email convert(EmailDto dto) {
        Email email = new Email();
        email.setEmail(dto.getEmail());
        return email;
    }
}
