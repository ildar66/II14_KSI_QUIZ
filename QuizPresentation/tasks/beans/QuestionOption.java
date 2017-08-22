package ru.cboss.contest.modules.tasks.beans;

import java.util.Date;

/**
 * QuestionOption for ProblemTask bean.
 *
 * Created by ishafigullin on 11.07.2017.
 */
public class QuestionOption extends BasePeriodicityEntity<Long>{
    private Long questionId;
    private Integer seqNo;
    private String body;
    private String bodyImg;

    public QuestionOption() {
        super();
    }

    public QuestionOption(Long id) {
        super(id);
    }

    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyImg() {
        return bodyImg;
    }
    public void setBodyImg(String bodyImg) {
        this.bodyImg = bodyImg;
    }

    @Override
    public String toString() {
        return "QuestionOption{" +
                "id=" + getId() +
                ", seqNo=" + seqNo +
                '}';
    }
}
