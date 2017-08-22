package ru.cboss.contest.modules.tasks.beans;

import java.util.Date;

/**
 * Period TO.
 *
 * Created by ishafigullin on 09.08.2017.
 */
public class Period {
    private Date start;
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
