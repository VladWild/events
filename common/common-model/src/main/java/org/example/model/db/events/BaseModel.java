package org.example.model.db.events;

import java.io.Serializable;

public abstract class BaseModel<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    protected T id;

    public BaseModel() {
    }

    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (this == o) {
            return true;
        } else if (!(o instanceof BaseModel)) {
            return false;
        } else {
            BaseModel that = (BaseModel)o;
            if (!that.getClass().equals(this.getClass())) {
                return false;
            } else {
                return this.id != null && this.id.equals(that.id);
            }
        }
    }

    public int hashCode() {
        return this.id != null ? this.id.hashCode() : 0;
    }

    public String toString() {
        return this.getClass().getSimpleName() + "{id=" + this.id + '}';
    }
}