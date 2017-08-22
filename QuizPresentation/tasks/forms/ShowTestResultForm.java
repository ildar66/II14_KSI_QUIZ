package ru.cboss.contest.modules.tasks.forms;

import ru.cboss.contest.modules.tasks.beans.Answer;
import ru.cboss.contest.modules.tasks.beans.TaskResult;

/**
 * Show Test result Form.
 *
 * Created by ishafigullin on 18.07.2017.
 */
public class ShowTestResultForm {
    TaskResult result;

    public TaskResult getResult() {
        return result;
    }
    public void setResult(TaskResult result) {
        this.result = result;
    }

    public String optionSelectionAttr(Long questionId, Long optionId) {
        String option = getAnswerBody(questionId);
        if(option != null) {
            return option.equals(optionId.toString()) ? "checked" : "";
        }
        return "";
    }

    private String getAnswerBody(Long questionId) {
        Answer answer = result.getAnswers().get(questionId);
        return (answer != null) ? answer.getBody() : null;
    }

    public String categorySelectionAttr(Long questionId, Long optionId) {
        String categories = getAnswerBody(questionId);
        if(categories != null) {
            String[] categoryArray = arrayFromString(categories);
            for(int i=0; i < categoryArray.length; ++i) {
                if(categoryArray[i].equals(optionId.toString()))
                    return "checked";
            }
        }
        return "";
    }

    private String[] arrayFromString(String body){
        return body.replace("[", "").replace("]", "").split(", ");
    }

    public String getAnswerComment(Long questionId) {
        Answer answer = result.getAnswers().get(questionId);
        return (answer != null) ? answer.getDsc() : "";
    }

    public Integer getAnswerConfidence(Long questionId) {
        Answer answer = result.getAnswers().get(questionId);
        return (answer != null) ? answer.getConfidence() : Answer.DEFAULT_CONFIDENCE;
    }

    public String getUserOptionValue(Long questionId) {
        Answer answer = result.getAnswers().get(questionId);
        return (answer != null) ? answer.getTxt() : "";
    }
}
