package com.cboss.ksi.dao;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.common.util.SortCriterion;

import java.util.*;

/**
 * Created by ishafigullin on 21.06.2017.
 */
public class DictionaryDaoMockImpl implements DictionaryDao {

    public final static String ALL_PATTERN = "*";
    private List<Problem> problems;

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public List<Problem> getProblems(int startIndex, int amount, String namePattern, List<SortCriterion> sortCriteria) {
        List<Problem> matches = new ArrayList<Problem>();
        for (Problem problem : problems) {
            if (isMatch(namePattern, problem)) {
                matches.add(problem);
            }
        }
        return matches;
    }

    private boolean isMatch(String namePattern, Problem problem) {
        if (namePattern.equals(ALL_PATTERN)) {
            return true;
        }
        return namePattern != null && problem.getBody().contains(namePattern);
    }

    @Override
    public int getProblemCount(String namePattern) {
        int result = 0;
        for (Problem problem : problems) {
            if (isMatch(namePattern, problem)) {
                result++;
            }
        }
        return result;
    }

    @Override
    public Problem getProblem(Long problemId) {
        return null; // TODO
    }

    @Override
    public int getQuestionsCount(Long problemId) {
        return 0; // TODO
    }

    @Override
    public List<Question> getQuestions(int startIndex, int i, Long problemId, List<SortCriterion> sortCriteria) {
        return null; // TODO
    }

    @Override
    public void changeProblem(Problem problem) {
        return; // TODO
    }

    @Override
    public void createProblem(Problem problem) {
        return; // TODO
    }

    @Override
    public void deleteProblem(Long problemId) {
        return; // TODO
    }

    @Override
    public Question getQuestion(Long questionId) {
        return null; // TODO
    }

    @Override
    public List<QuestionOption> getQuestionOptions(int startIndex, int amount, Long questionId, List<SortCriterion> sortCriteria) {
        return null; // TODO
    }

    @Override
    public void changeQuestion(Question question) {
        // TODO
    }

    @Override
    public void createQuestion(Question question) {
        // TODO
    }

    @Override
    public void deleteQuestion(Long questionId) {
        // TODO
    }

    @Override
    public void createQuestionOption(QuestionOption option) {
        // TODO
    }

    @Override
    public QuestionOption getQuestionOption(Long optionId) {
        return null; // TODO
    }

    @Override
    public void changeQuestionOption(QuestionOption option) {
        // TODO
    }

    @Override
    public void deleteQuestionOption(Long optionId) {
        // TODO
    }
}
