package org.example.model.db.events;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseCreated<T> extends BaseModel<T> {
    protected LocalDateTime created;
    protected String createdBy;

    @Column(name = "CREATED", updatable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "CREATED_BY", updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @PrePersist
    public void onPrePersist() {
        created = LocalDateTime.now();
        createdBy = createdBy == null ? getUser() : createdBy;
    }

    @Transient
    protected String getUser() {
        //тут может быть метод взятия логина пользователя
        //пока можно условно всегда возврящаем systemUser
        return "systemUser";
    }
}
