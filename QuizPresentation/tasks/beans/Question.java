package ru.cboss.contest.modules.tasks.beans;

import java.util.List;

/**
 * Question for Problem task bean.
 *
 * Created by ishafigullin on 10.07.2017.
 */
public class Question extends BasePeriodicityEntity<Long>{
    private Long problemId;
    private Integer seqNo;
    private String body;
    private String bodyImg;
    private OptionsType optionsType;
    private String mandatory;
    private boolean userOption;
    private String userOptionLabel;
    private List<QuestionOption> options;

    public Question(){
        super();
    }

    public Question(Long id) {
        super(id);
    }

    public Long getProblemId() {
        return problemId;
    }
    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }
    public void setOptions(List<QuestionOption> options) {
        this.options = options;
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

    public boolean isUserOption() {
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + getId() +
                ", seqNo=" + seqNo + "," +
                " optionsType=" + optionsType +
                " options=" + options +
                '}';
    }
}
