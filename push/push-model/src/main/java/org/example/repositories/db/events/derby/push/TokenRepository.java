package org.example.repositories.db.events.derby.push;

import org.example.model.db.events.derby.push.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
