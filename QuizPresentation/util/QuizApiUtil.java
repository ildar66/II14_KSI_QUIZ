package ru.cboss.contest.util;

import org.apache.ibatis.session.SqlSession;
import ru.cboss.config.MyBatisConfig;
import ru.cboss.config.mappers.QuizApiMapper;
import ru.cboss.contest.modules.tasks.beans.ProblemTask;

import java.util.HashMap;
import java.util.List;

/**
 * Utility for Quiz API processing actions.
 *
 * Created by ishafigullin on 03.08.2017.
 */
public class QuizApiUtil {
    public static ProblemTask getTask(Long taskId) {
        ProblemTask task = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            // ProblemTaskMapper mapper = session.getMapper(ProblemTaskMapper.class);
            // task = mapper.getTask(taskId);

            //task = session.selectOne("ru.cboss.config.mappers.QuizApiMapper.getQuiz", taskId);
            QuizApiMapper mapper = session.getMapper(QuizApiMapper.class);
            task = mapper.getQuiz(taskId);

        } finally {
            session.close();
        }
        return task;
    }

    public static List<ProblemTask> getTaskList(Long userID) {
        List<ProblemTask> resultList = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            QuizApiMapper mapper = session.getMapper(QuizApiMapper.class);
            // resultList = mapper.getTaskList(userID);
            HashMap map = new HashMap();
            map.put("userId", 333);
            map.put("flag", 0);
            map.put("taskList", new java.util.ArrayList<ProblemTask>());
            mapper.getTaskMap(map);
        } finally {
            session.close();
        }
        return resultList;
    }
}
