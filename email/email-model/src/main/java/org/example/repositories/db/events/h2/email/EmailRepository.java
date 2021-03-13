package org.example.repositories.db.events.h2.email;

import org.example.model.db.events.h2.email.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
