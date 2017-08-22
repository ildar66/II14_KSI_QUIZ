package ru.cboss.contest.modules.tasks.forms;

import ru.cboss.contest.modules.tasks.beans.*;
import ru.cboss.contest.util.ProblemTaskUtil;
import ru.cboss.core.user.UserProfile;

import java.util.List;

/**
 * Show Task(Problem) Form.
 *
 * Created by ishafigullin on 10.07.2017.
 */
public class ShowTaskForm {
    String option;
    String[]  categories;
    Answer currentAnswer;
    Question currentQuestion;
    ProblemTask currentTask;
    TestEventInfo info;

    public TestEventInfo getInfo() {
        return info;
    }
    public void setInfo(TestEventInfo info) {
        this.info = info;
    }

    public ProblemTask getCurrentTask() {
        return currentTask;
    }
    public void setCurrentTask(ProblemTask currentTask) {
        this.currentTask = currentTask;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }
    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public Object getComment() {
        if(currentAnswer != null && currentAnswer.getDsc() != null) {
            return currentAnswer.getDsc();
        } else {
            return "";
        }
    }

    public Integer getConfidence() {
        if(currentAnswer != null && currentAnswer.getConfidence() != null) {
            return currentAnswer.getConfidence();
        } else {
            return Answer.DEFAULT_CONFIDENCE;
        }
    }


    public Object getUserOptionValue() {
        if(currentAnswer != null && currentAnswer.getTxt() != null) {
            return currentAnswer.getTxt();
        } else {
            return "";
        }
    }

    public void setCurrentAnswer(Answer currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public String[] getCategories() { return categories; }
    public void setCategories(String[] categories) {
        this.categories = categories;
    }
    public String categorySelectionAttr(String category) {
        if(categories != null) {
            for(int i=0; i < categories.length; ++i) {
                if(categories[i].equals(category))
                    return "checked";
            }
        }
        return "";
    }

    public void setOption(String s) { option = s; }
    public String getOption() {
        return option != null ? option : "";
    }
    public String optionSelectionAttr(String optionName) {
        if(option != null) {
            return option.equals(optionName) ? "checked" : "";
        }
        return "";
    }

    public List<QuestionOption> getOptionList() {
        return ProblemTaskUtil.getOptionsForQuestion(currentQuestion.getId());
    }

    public void init(UserProfile userProfile, Question question) {
        setCurrentQuestion(question);
        ProblemTask task = ProblemTaskUtil.getTask(question.getProblemId());
        setCurrentTask(task);
        TestEventInfo info = ProblemTaskUtil.getTestEventInfo(task, userProfile);
        setInfo(info);
        Answer lastAnswer = ProblemTaskUtil.getLastAnswerForUserQuestion(userProfile.getUserId(), question.getId());
        if(lastAnswer != null){
            setCurrentAnswer(lastAnswer);
            OptionsType optionsType = question.getOptionsType();
            if(optionsType == OptionsType.MULTIPLE){
                setCategories(arrayFromString(lastAnswer.getBody()));
            } else if (optionsType == OptionsType.UNIQUE){
                setOption(lastAnswer.getBody());
            }
        }
    }

    private String[] arrayFromString(String body){
        return body.replace("[", "").replace("]", "").split(", ");
    }
}
