package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.cboss.contest.modules.tasks.beans.Question;

import java.util.List;

/**
 * myBatis Question Mapper for ProblemTask.
 *
 * Created by ishafigullin on 10.07.2017.
 */
public interface QuestionMapper {
    final String GET_BY_TASK_ID = "select n, r_problem_id, seq_no, body, body_img, fd, td, d_options_type, user_option, user_option_label" +
            " from KSI2016_question where r_problem_id = #{taskId} order by seq_no";

    final String GET_BY_ID = "select n, r_problem_id, seq_no, body, fd, td, d_options_type" +
            " from KSI2016_question where n = #{questionID}";

    @Select(GET_BY_TASK_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="problemId", column="r_problem_id"),
            @Result(property="seqNo", column="seq_no"),
            @Result(property="bodyImg", column="body_img"),
            @Result(property="userOptionLabel", column="user_option_label"),
            @Result(property="userOption", column="user_option"),
            @Result(property="optionsType", column="d_options_type", typeHandler = OptionsTypeTypeHandler.class)
    })
    List<Question> getQuestionsForTask(Long taskID);

    @Select(GET_BY_TASK_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="problemId", column="r_problem_id"),
            @Result(property="seqNo", column="seq_no"),
            @Result(property="bodyImg", column="body_img"),
            @Result(property="userOptionLabel", column="user_option_label"),
            @Result(property="userOption", column="user_option"),
            @Result(property="optionsType", column="d_options_type", typeHandler = OptionsTypeTypeHandler.class),
            @Result(property="options", column="n", javaType=List.class,
                    many=@Many(select = "ru.cboss.config.mappers.QuestionOptionMapper.getOptionsForQuestion"))
    })
    List<Question> getQuestionsWithOptionsForTask(Long taskID);

    @Select(GET_BY_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="problemId", column="r_problem_id"),
            @Result(property="seqNo", column="seq_no"),
            @Result(property="optionsType", column="d_options_type", typeHandler = OptionsTypeTypeHandler.class)
    })
    Question getQuestion(Long questionID);
}
