package org.example.service.email;

import org.example.common.asserts.AssertEvents;
import org.example.model.db.events.h2.email.Email;
import org.example.repositories.db.events.h2.email.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    public void saveEmail(Email email) {
        emailRepository.save(email);
    }

    public void changeEmail(Email source) {
        Email emailDb = AssertEvents.optionalNotNull(
                emailRepository.findById(source.getId()),
                String.format("Нету email'а в базе с id = %s", source.getId())
        );
        emailDb.setEmail(source.getEmail());
        emailRepository.save(emailDb);
    }

    public void deleteEmail(Long id) {
        emailRepository.deleteById(id);
    }
}
