package com.cboss.ksi.dao;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.common.util.SortCriterion;
import com.cboss.ksi.dao.mapper.ProblemRowMapper;
import com.cboss.ksi.dao.mapper.QuestionOptionRowMapper;
import com.cboss.ksi.dao.mapper.QuestionRowMapper;
import com.cboss.ksi.dao.util.AbstractDao;

import java.sql.Types;
import java.util.List;


/**
 * DAO implementation for Dictionaries
 *
 * Created by ishafigullin on 22.06.2017.
 */
public class DictionaryDaoImpl extends AbstractDao implements DictionaryDao{

    @Override
    public List<Problem> getProblems(int startIndex, int amount, String namePattern, List<SortCriterion> sortCriteria) {
        Object[] args = { namePattern };
        return getJdbcTemplate().query(sql.get("PROBLEMS_BY_NAME"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public int getProblemCount(String namePattern) {
        Object[] args = { namePattern };
        return getJdbcTemplate().queryForInt(sql.get("PROBLEMS_BY_NAME_COUNT"), args);
    }

    @Override
    public Problem getProblem(Long problemId) {
        Object[] args = { problemId };
        return getJdbcTemplate().queryForObject(sql.get("GET_PROBLEM"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public int getQuestionsCount(Long problemId) {
        Object[] args = { problemId };
        return getJdbcTemplate().queryForInt(sql.get("QUESTIONS_COUNT"), args);
    }

    @Override
    public List<Question> getQuestions(int startIndex, int amount, Long problemId, List<SortCriterion> sortCriteria) {
        Object[] args = { problemId };
        return getJdbcTemplate().query(sql.get("QUESTIONS_FOR_PROBLEM"), args, QuestionRowMapper.INSTANCE);
    }

    @Override
    public void changeProblem(Problem problem) {
        Object[] args = { problem.getSeqNo(), problem.getBody(), problem.getFromDate(), problem.getToDate(),
                          problem.getLimitTime(), problem.getStatus().getId(), problem.getRound(), problem.<Long>getId() };
        getJdbcTemplate().update(sql.get("CHANGE_PROBLEM"), args);
    }

    @Override
    public void createProblem(Problem problem) {
        Object[] args = { problem.getSeqNo(), problem.getSeqNo(), problem.getBody(), problem.getFromDate(), problem.getToDate(),
                          problem.getLimitTime(), problem.getStatus().getId(), problem.getRound() };
        getJdbcTemplate().update(sql.get("CREATE_PROBLEM"), args);
    }

    @Override
    public void deleteProblem(Long problemId) {
        Object[] args = { problemId };
        getJdbcTemplate().update(sql.get("DELETE_PROBLEM"), args);
    }

    @Override
    public Question getQuestion(Long questionId) {
        Object[] args = { questionId };
        return getJdbcTemplate().queryForObject(sql.get("GET_QUESTION"), args, QuestionRowMapper.INSTANCE);
    }

    @Override
    public List<QuestionOption> getQuestionOptions(int startIndex, int amount, Long questionId, List<SortCriterion> sortCriteria) {
        Object[] args = { questionId };
        return getJdbcTemplate().query(sql.get("GET_QUESTION_OPTIONS"), args, QuestionOptionRowMapper.INSTANCE);
    }

    @Override
    public void changeQuestion(Question question) {
        String userOptionLabel = question.getUserOptionLabel() != null ? question.getUserOptionLabel() : "";
        Object[] args = { question.getSeqNo(), question.getBody(), question.getFromDate(), question.getToDate(),
                question.getOptionsType().getId(), question.getUserOption() ? 1 : 0, userOptionLabel,
                question.<Long>getId() };
        getJdbcTemplate().update(sql.get("CHANGE_QUESTION"), args);
    }

    @Override
    public void createQuestion(Question question) {
        String userOptionLabel = question.getUserOptionLabel() != null ? question.getUserOptionLabel() : "";
        Object[] args = { question.getProblemId(), question.getSeqNo(), question.getBody(), question.getFromDate(), question.getToDate(),
                question.getOptionsType().getId(), question.getUserOption() ? 1 : 0, userOptionLabel};
        getJdbcTemplate().update(sql.get("CREATE_QUESTION"), args);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Object[] args = { questionId };
        getJdbcTemplate().update(sql.get("DELETE_QUESTION"), args);
    }

    @Override
    public void createQuestionOption(QuestionOption option) {
        String body = (option.getBody() != null) ? option.getBody() : "";
        String bodyImg = (option.getBodyImg() != null) ? option.getBodyImg() : "";
        Object[] args = { option.getQuestionId(), option.getSeqNo(), body, bodyImg, option.getFromDate(), option.getToDate()};
        getJdbcTemplate().update(sql.get("CREATE_QUESTION_OPTION"), args);
    }

    @Override
    public QuestionOption getQuestionOption(Long optionId) {
        Object[] args = { optionId };
        return getJdbcTemplate().queryForObject(sql.get("GET_QUESTION_OPTION"), args, QuestionOptionRowMapper.INSTANCE);
    }

    @Override
    public void changeQuestionOption(QuestionOption option) {
        String body = (option.getBody() != null) ? option.getBody() : "";
        String bodyImg = (option.getBodyImg() != null) ? option.getBodyImg() : "";
        Object[] args = { option.getSeqNo(), body, bodyImg, option.getFromDate(), option.getToDate(), option.<Long>getId() };
        getJdbcTemplate().update(sql.get("CHANGE_QUESTION_OPTION"), args);
    }

    @Override
    public void deleteQuestionOption(Long optionId) {
        Object[] args = { optionId };
        getJdbcTemplate().update(sql.get("DELETE_QUESTION_OPTION"), args);
    }
}
