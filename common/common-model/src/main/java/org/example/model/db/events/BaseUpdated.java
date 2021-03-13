package org.example.model.db.events;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseUpdated<T> extends BaseCreated<T> {
    protected LocalDateTime lastUpdate;
    protected String lastUpdateBy;

    @Column(name = "LAST_UPD")
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(name = "LAST_UPD_BY")
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @PrePersist
    public void onPrePersist() {
        super.onPrePersist();
        lastUpdate = created;
        lastUpdateBy = createdBy;
    }

    @PreUpdate
    public void onPreUpdate() {
        lastUpdate = LocalDateTime.now();
        lastUpdateBy = lastUpdateBy == null ? getUser() : lastUpdateBy;
    }
}
