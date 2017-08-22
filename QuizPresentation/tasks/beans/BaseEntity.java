package ru.cboss.contest.modules.tasks.beans;

/**
 * Base abstract Entity.
 *
 * Created by ishafigullin on 01.08.2017.
 */
public abstract class BaseEntity<T> {
    private T id;


    public BaseEntity() {}

    public BaseEntity(T id) {
        setId(id);
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity<?> that = (BaseEntity<?>) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
