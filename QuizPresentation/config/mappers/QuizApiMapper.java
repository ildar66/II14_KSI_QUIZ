package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import ru.cboss.contest.modules.tasks.beans.ProblemTask;

import java.util.HashMap;
import java.util.List;

/**
 * myBatis Mapper for Quiz API.
 *
 * Created by ishafigullin on 03.08.2017.
 */
public interface QuizApiMapper {
    final String GET_BY_ID = "select n, seq_no seqNo, body, fd, td, limit_time_sec, d_status from ksi2016_problem " +
            "where n = #{taskId}";

    final String GET_TASK_LIST =
            "{CALL #{outParam, javaType=java.sql.ResultSet, jdbcType=CURSOR, mode=OUT} := " +
                    "KSI_QUIZ_API.getTasksListX(#{param1, javaType=Long, mode=IN})}";
    /*final String GET_TASK_LIST =
            "{CALL KSI_QUIZ_API.getTasksListX( #{userID, j  avaType=Long, mode=IN} )}";*/
    //final String GET_TASK_LIST = "{call getTasksListX(3706, 0)}";
    //final String GET_TASK_LIST = "{CALL tasks_model_typX()}";

    @Select(GET_BY_ID)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="seqNo", column="seq_no"),
            @Result(property="fromDate", column="fd"),
            @Result(property="toDate", column="td"),
            @Result(property="limitTime", column="limit_time_sec")
    })
    ProblemTask getQuiz(Long taskId);

    @Select(GET_TASK_LIST)
    /*@Select("{ CALL #{outParam, jdbcType=NUMERIC, mode=OUT} := getTasksListX( "
            + "#{pNum1, jdbcType=NUMERIC, mode=IN},"
            + "#{pNum2, jdbcType=NUMERIC, mode=IN},"
            + "#{pNum3, jdbcType=NUMERIC, mode=IN} )}")*/
    @Options(statementType = StatementType.CALLABLE)
    // @ResultType(ProblemTask.class)
    @Results(value = {
            @Result(property="id", column="n", javaType = Long.class),
            @Result(property="seqNo", column="seq_no")
    })
    List<ProblemTask> getTaskList(Long userID);

    @Options(statementType = StatementType.CALLABLE)
    @Select(" {call KSI_QUIZ_API.getTasksListX( #{userId, mode=IN}," +  "#{flag, mode=IN}," +
           /* " #{taskList, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet, resultMap=taskList} ) }")*/
            " #{taskList, mode=OUT, jdbcType=CURSOR, javaType=java.sql.ResultSet} ) }")
    void getTaskMap(HashMap params);
}
