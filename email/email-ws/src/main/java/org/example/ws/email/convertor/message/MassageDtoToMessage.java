package org.example.ws.email.convertor.message;

import org.example.model.db.events.h2.email.Email;
import org.example.model.db.events.h2.email.EmailSendInfo;
import org.example.ws.email.dto.message.MessageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static org.example.utils.DbUtils.getEmptyEntity;

@Component
public class MassageDtoToMessage implements Converter<MessageDto, EmailSendInfo> {

    @Override
    public EmailSendInfo convert(MessageDto dto) {
        EmailSendInfo info = new EmailSendInfo();
        info.setMassage(dto.getMessage());
        info.setEmail(getEmptyEntity(Email.class, dto.getEmailId()));
        return info;
    }
}
