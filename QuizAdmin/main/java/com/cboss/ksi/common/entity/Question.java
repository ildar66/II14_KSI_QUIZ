package com.cboss.ksi.common.entity;

import java.util.Date;

/*
 * Question VO.
 *
 * Created by ishafigullin on 26.06.2017.
 */
public class Question extends BasePeriodicityEntity<Long>{
    private Long problemId;
    private Integer seqNo;
    private String body;
    private OptionsType optionsType;
    private String mandatory;
    private boolean userOption;
    private String userOptionLabel;

    public Question(Long id) {
            super(id);
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

    public OptionsType getOptionsType() {
        return optionsType;
    }
    public void setOptionsType(OptionsType optionsType) {
        this.optionsType = optionsType;
    }

    public String getMandatory() {
        return mandatory;
    }
    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public Long getProblemId() {
        return problemId;
    }
    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public boolean getUserOption() {
        return userOption;
    }
    public void setUserOption(boolean userOption) {
        this.userOption = userOption;
    }

    public String getUserOptionLabel() {
        return userOptionLabel;
    }
    public void setUserOptionLabel(String userOptionLabel) {
        this.userOptionLabel = userOptionLabel;
    }
}
