package ru.cboss.contest.modules.tasks.beans;

import java.util.List;
import java.util.Map;

/**
 * Test result for ProblemTask.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public class TaskResult extends ProblemTask{
    List<Question> questions;
    Map<Long, Answer> answers;

    public Map<Long, Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(Map<Long, Answer> answers) {
        this.answers = answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "questions=" + questions +
                '}';
    }
}
