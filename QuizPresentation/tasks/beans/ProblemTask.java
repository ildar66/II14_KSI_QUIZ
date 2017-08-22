package ru.cboss.contest.modules.tasks.beans;

import java.util.Date;

/**
 * Problem task bean.
 *
 * Created by ishafigullin on 29.06.2017.
 */
public class ProblemTask extends BasePeriodicityEntity<Long>{
    private Integer seqNo;
    private String body;
    private String bodyImg;
    private State state = State.NEW;
    private Integer limitTime;

    public ProblemTask(){
        super();
    }

    public ProblemTask(Long id){
        super(id);
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return body;
    }

    public String getBodyImg() {
        return bodyImg;
    }
    public void setBodyImg(String bodyImg) {
        this.bodyImg = bodyImg;
    }

    public State getState() {
        return state;
    }

    public String getStyleClass(){
        if(state == State.NEW){
            return "main-button";
        } else {
            return "main-button-no-active";
        }
    }

    public static enum State{
        NEW(-1), OPEN(0), SOLVE(1), RATED(2), EXPIRED(3), REFUSED(4);

        private int id;

        private State(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }

    public Integer getLimitTime() {
        return limitTime;
    }
    public void setLimitTime(Integer limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public String toString() {
        return "ProblemTask{" +
                "seqNo=" + seqNo +
                ", id=" + getId() +
                '}';
    }
}
