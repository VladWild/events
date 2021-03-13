package org.example.model.db.events.h2.email;

import org.example.model.db.events.BaseCreated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "email_send_info")
public class EmailSendInfo extends BaseCreated<Long> {

    private String massage;
    private Email email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "email_send_info_seq")
    @SequenceGenerator(name = "email_send_info_seq", sequenceName = "email_send_info_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @ManyToOne
    @JoinColumn(name = "email_id")
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
