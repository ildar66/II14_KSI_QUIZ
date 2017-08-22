package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import ru.cboss.contest.modules.tasks.beans.Period;
import ru.cboss.contest.modules.tasks.beans.ProblemTask;

import java.util.Date;
import java.util.List;

/**
 * myBatis Mapper for ProblemTask.
 *
 * Created by ishafigullin on 13.06.2017.
 */
public interface ProblemTaskMapper {
    final int CURRENT_ROUND = 1; // TODO maybe get from properties

    final String GET_START_TIME_FOR_USER_TASK = "select NVL(min(fd), sysdate) from ksi2016_event " +
            " where r_user_id = #{userID} and obj_id = #{taskID} and dsc = 'OPEN' ";

    final String GET_LIFE_PERIOD_FOR_USER_TASK = "select NVL(min(fd), sysdate) \"start\", sysdate \"end\" " +
            " from ksi2016_event " +
            " where r_user_id = #{userID} and obj_id = #{taskID} and dsc = 'OPEN' ";

    final String GET_PROBLEM_STATE =
           "(select dsc from ksi2016_event" +
                   " where r_user_id = #{userID} and obj_id = p.n and fd = " +
                   " (select max(fd) from ksi2016_event where r_user_id = #{userID} and obj_id = p.n)) state ";

    final String GET_NON_BLOCKING_USER_PROBLEMS =
           " select n, seq_no seqNo, body, fd, td, limit_time_sec, d_status, round, " + GET_PROBLEM_STATE +
           " from ksi2016_problem p where d_status = 1 and round = " + CURRENT_ROUND +
           " and n not in( select r_problem_id from ksi_user_blocking where r_user_id = #{userID} ) " +
           " union " +
           " select n, seq_no seqNo, body, fd, td, limit_time_sec, d_status, round, " + GET_PROBLEM_STATE +
           " from ksi2016_problem p " +
           " where n in( select r_problem_id from ksi_problem_access where r_user_id = #{userID} ) " +
           " order by seqNo ";

    final String GET_BY_ID = "select n, seq_no seqNo, body, body_img, fd, td, limit_time_sec, d_status from ksi2016_problem " +
                            "where n = #{taskId}";

    @Select(GET_NON_BLOCKING_USER_PROBLEMS)
    @Results(value = {
           @Result(property="id", column="n", javaType = Long.class),
           @Result(property="seqNo", column="seq_no")
    })
   List<ProblemTask> getTasks(Long userID);

    @Select(GET_BY_ID)
    @Results(value = {
           @Result(property="id", column="n", javaType = Long.class),
           @Result(property="seqNo", column="seq_no"),
           @Result(property="bodyImg", column="body_img"),
           @Result(property="fromDate", column="fd"),
           @Result(property="toDate", column="td"),
           @Result(property="limitTime", column="limit_time_sec")
    })
    ProblemTask getTask(Long taskId);

    @Select(GET_START_TIME_FOR_USER_TASK)
    Date getStartTimeForUserTask(@Param("userID") Long userId, @Param("taskID") Long taskId);

    @Select(GET_LIFE_PERIOD_FOR_USER_TASK)
    Period getLifePeriodForUserTask(@Param("userID") Long userId, @Param("taskID") Long taskId);
}
