package org.example.model.db.events.derby.push;

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
        name = "tokens",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "token")
)
public class Token extends BaseUpdated<Long> {
    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "token_seq")
    @SequenceGenerator(name = "token_seq", sequenceName = "token_seq", allocationSize = 1)
    public Long getId() {
        return super.getId();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
