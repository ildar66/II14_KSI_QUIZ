package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.cboss.contest.modules.tasks.beans.QuestionOption;

import java.util.List;

/**
 *  myBatis QuestionOption Mapper for ProblemTask.
 *
 * Created by ishafigullin on 11.07.2017.
 */
public interface QuestionOptionMapper {
    final String GET_BY_QUESTION_ID = "select n, r_question_id, seq_no, body, utl_raw.cast_to_varchar2(body_img) bodyImg, dsc " +
            " from KSI_question_option where r_question_id = #{questionID} order by seq_no";

    @Select(GET_BY_QUESTION_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="questionId", column="r_question_id"),
            @Result(property="seqNo", column="seq_no")
    })
    List<QuestionOption> getOptionsForQuestion(Long questionID);

}
