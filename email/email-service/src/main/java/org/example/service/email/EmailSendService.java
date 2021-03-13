package org.example.service.email;

import org.example.common.asserts.AssertEvents;
import org.example.model.db.events.h2.email.EmailSendInfo;
import org.example.repositories.db.events.h2.email.EmailRepository;
import org.example.repositories.db.events.h2.email.EmailSendInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSendService {

    private final EmailSendInfoRepository emailSendInfoRepository;
    private final EmailRepository emailRepository;

    public EmailSendService(EmailSendInfoRepository emailSendInfoRepository,
                            EmailRepository emailRepository) {
        this.emailSendInfoRepository = emailSendInfoRepository;
        this.emailRepository = emailRepository;
    }

    public void sendMessage(EmailSendInfo source) {
        checkEmailInDb(source.getEmail().getId());
        //логика отправки сообщения по email
        emailSendInfoRepository.save(source);
    }

    public List<EmailSendInfo> getEmailSendInfo() {
        return emailSendInfoRepository.findAll();
    }

    private void checkEmailInDb(Long emailId) {
        AssertEvents.optionalNotNull(
                emailRepository.findById(emailId),
                String.format("Нету email'а в базе с id = %s", emailId)
        );
    }
}
