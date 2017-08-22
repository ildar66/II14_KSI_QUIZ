package com.cboss.ksi.dao;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.common.util.SortCriterion;

import java.util.List;

/**
 * DAO for Dictionaries
 *
 * Created by ishafigullin on 21.06.2017.
 */
public interface DictionaryDao {
    List<Problem> getProblems(int startIndex, int amount, String namePattern, List<SortCriterion> sortCriteria);

    int getProblemCount(String namePattern);

    Problem getProblem(Long problemId);

    int getQuestionsCount(Long problemId);

    List<Question> getQuestions(int startIndex, int amount, Long problemId, List<SortCriterion> sortCriteria);

    void changeProblem(Problem problem);

    void createProblem(Problem problem);

    void deleteProblem(Long problemId);

    Question getQuestion(Long questionId);

    List<QuestionOption> getQuestionOptions(int startIndex, int amount, Long questionId, List<SortCriterion> sortCriteria);

    void changeQuestion(Question question);

    void createQuestion(Question question);

    void deleteQuestion(Long questionId);

    void createQuestionOption(QuestionOption option);

    QuestionOption getQuestionOption(Long optionId);

    void changeQuestionOption(QuestionOption option);

    void deleteQuestionOption(Long optionId);
}
