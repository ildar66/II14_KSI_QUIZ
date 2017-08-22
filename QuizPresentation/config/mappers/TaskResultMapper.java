package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.cboss.contest.modules.tasks.beans.TaskResult;

import java.util.List;

/**
 * myBatis Mapper for TestResult.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public interface TaskResultMapper {
   final String GET_BY_ID = "select n id, seq_no seqNo, body, fd, td, limit_time_sec, d_status from ksi2016_problem" +
                            " where n = #{taskId}";

   @Select(GET_BY_ID)
   @Results(value = {
           @Result(property="id", column="n", javaType = Long.class),
           @Result(property="seqNo", column="seq_no"),
           @Result(property="fromDate", column="fd"),
           @Result(property="toDate", column="td"),
           @Result(property="limitTime", column="limit_time_sec"),
           @Result(property="questions", column="id", javaType=List.class,
                   many=@Many(select = "ru.cboss.config.mappers.QuestionMapper.getQuestionsWithOptionsForTask"))
   })
   TaskResult getTaskResult(Long taskId);

}
