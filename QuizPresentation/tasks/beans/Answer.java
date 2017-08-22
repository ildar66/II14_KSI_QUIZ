package ru.cboss.contest.modules.tasks.beans;

/**
 * Answer for question bean.
 *
 * Created by ishafigullin on 13.07.2017.
 */
public class Answer extends BasePeriodicityEntity<Long>{
    private Long questionId;
    private Long userId;
    private String body;
    private String dsc;
    private Integer confidence;
    private String txt;

    public final static Integer DEFAULT_CONFIDENCE = Integer.valueOf(100);

    public Answer() {}

    public Answer(Long id) {
        super(id);
    }

    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getDsc() {
        return dsc;
    }
    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Integer getConfidence() {
        return confidence;
    }
    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getTxt() {
        return txt;
    }
    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + getId() +
                ", problemId=" + questionId +
                ", userId=" + userId +
                ", body='" + body + '\'' +
                ", dsc='" + dsc + '\'' +
                ", confidence='" + confidence + '\'' +
                '}';
    }
}
