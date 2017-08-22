package ru.cboss.contest.modules.tasks.beans;

import java.util.Date;

/**
 * Info about user test.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public class TestEventInfo {
    private String timeLimit;
    private String countQuestions;
    private Date startTime;
    private Long ksiNumber;

    public String getTimeLimit() {
        return timeLimit;
    }
    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getCountQuestions() {
        return countQuestions;
    }
    public void setCountQuestions(String countQuestions) {
        this.countQuestions = countQuestions;
    }

    public Long getKsiNumber() {
        return ksiNumber;
    }
    public void setKsiNumber(Long ksiNumber) {
        this.ksiNumber = ksiNumber;
    }
}
