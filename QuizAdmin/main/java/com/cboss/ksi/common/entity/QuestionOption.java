package com.cboss.ksi.common.entity;

import java.util.Date;

/**
 * Option for Question VO.
 *
 * Created by ishafigullin on 30.06.2017.
 */
public class QuestionOption extends BasePeriodicityEntity<Long>{
    private Long questionId;
    private Integer seqNo;
    private String body;
    private String bodyImg;

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
}
