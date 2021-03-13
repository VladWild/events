package org.example.model.db.events.h2.email;

import org.example.model.db.events.BaseUpdated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "emails",
        uniqueConstraints=
        @UniqueConstraint(columnNames="email")
)
public class Email extends BaseUpdated<Long> {
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "email_seq")
    @SequenceGenerator(name = "email_seq", sequenceName = "email_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
