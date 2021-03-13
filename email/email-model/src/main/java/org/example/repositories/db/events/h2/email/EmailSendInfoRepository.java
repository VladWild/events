package org.example.repositories.db.events.h2.email;

import org.example.model.db.events.h2.email.EmailSendInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailSendInfoRepository extends JpaRepository<EmailSendInfo, Long> {
}
