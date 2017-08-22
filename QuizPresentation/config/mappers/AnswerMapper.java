package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.cboss.contest.modules.tasks.beans.Answer;

import java.util.List;

/**
 *  myBatis Mapper for Answer on Questions.
 *
 * Created by ishafigullin on 13.07.2017.
 */
public interface AnswerMapper {
    final String INSERT = "insert into ksi2016_ANSWER (n, fd, r_user_id, r_question_id, body, dsc, txt, confidence) "
            + "values(  ksi2016_ANSWER_SQ.nextval, sysdate, #{userId}, #{questionId}, #{body}, #{dsc}, #{txt}, #{confidence} )";

    final String GET_BY_USER_QUESTION_ID = "select n, fd, r_user_id, r_question_id, body, dsc, txt, confidence " +
            " from ksi2016_ANSWER where r_user_id = #{param1} and r_question_id = #{param2} order by fd desc";

    @Insert(INSERT)
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Answer answer);

    @Select(GET_BY_USER_QUESTION_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="questionId", column="r_question_id"),
            @Result(property="userId", column="r_user_id"),
    })
    List<Answer> getAnswersForUserQuestion(Long userID, Long questionID);
}
