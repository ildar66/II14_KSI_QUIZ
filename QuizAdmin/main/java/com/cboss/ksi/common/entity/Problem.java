package com.cboss.ksi.common.entity;

import java.util.Date;

/**
 * Problem(task) VO.
 *
 * Created by ishafigullin on 21.06.2017.
 */
public class Problem extends BasePeriodicityEntity<Long> {
    private Integer seqNo;
    private String body;
    private Integer limitTime;
    private Status status;
    private Integer round;

    public Problem(Long id) {
        super(id);
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public String getBody() {
        return body;
    }

    public Integer getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    public static enum Status{
        NOT_ACCESSIBLE(-1), ACCESSIBLE(1);

        private int id;

        private Status(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }
}
