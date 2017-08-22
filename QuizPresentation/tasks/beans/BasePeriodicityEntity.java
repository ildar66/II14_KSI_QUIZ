package ru.cboss.contest.modules.tasks.beans;

import java.util.Date;

/**
 * Base abstract Periodicity Entity.
 *
 * Created by ishafigullin on 01.08.2017.
 */
public abstract class BasePeriodicityEntity<T> extends BaseEntity<T>{

    private Date fromDate;
    private Date toDate;

    public BasePeriodicityEntity() {}

    public BasePeriodicityEntity(T id) {
        super(id);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
