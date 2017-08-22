package ru.cboss.contest.util;

import groovy.json.StringEscapeUtils;
import org.apache.ibatis.session.SqlSession;
import ru.cboss.config.MyBatisConfig;
import ru.cboss.config.mappers.*;
import ru.cboss.contest.modules.tasks.beans.*;
import ru.cboss.core.json.JSONException;
import ru.cboss.core.user.UserProfile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility for task processing actions.
 *
 * Created by ishafigullin on 29.06.2017.
 */
public class ProblemTaskUtil {
    private final static String MSG_FOR_NEW_TASK = "Внимание! Задание является дискриминирующим." +
            " Время на ответ ограничено: %d:%d {часы:минуты} с момента ознакомления." +
            " Задание доступно до конца дня %3$td.%3$tm.%3$tY." +
            " Невыполнение его в отведенное время  либо неознакомление с ним в указанный срок  может лишить Вас" +
            " возможности отвечать на другие вопросы КСИ, и прекратить Ваше дальнейшее участие в Конкурсе." +
            " Желаете ознакомиться с заданием именно сейчас?";
    private final static int SEC_IN_MIN = 60;
    private final static int SEC_IN_HOUR = SEC_IN_MIN * 60;

    public static List<ProblemTask> getTaskList(Long userID) {
        List<ProblemTask> resultList = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            ProblemTaskMapper mapper = session.getMapper(ProblemTaskMapper.class);
            resultList = mapper.getTasks(userID);
        } finally {
            session.close();
        }
        return resultList;
    }

    public static List<Question> getQuestionsForTask(Long taskID) {
        List<Question> resultList = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            QuestionMapper mapper = session.getMapper(QuestionMapper.class);
            resultList = mapper.getQuestionsForTask(taskID);
        } finally {
            session.close();
        }
        return resultList;
    }

    public static ProblemTask getTask(Long taskId) {
        ProblemTask task = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            ProblemTaskMapper mapper = session.getMapper(ProblemTaskMapper.class);
            task = mapper.getTask(taskId);
        } finally {
            session.close();
        }
        return task;
    }

    public static String checkTaskOpen(Long taskId) throws JSONException {
        ProblemTask task = getTask(taskId);
        int limitHours = task.getLimitTime() / SEC_IN_HOUR;
        int limitMin = (task.getLimitTime() % SEC_IN_HOUR) / SEC_IN_MIN;

        String json = "{code:1, msg:'" + MSG_FOR_NEW_TASK + "', pid:" + taskId + "}";
        json = String.format(json, limitHours, limitMin, task.getToDate());

        return StringEscapeUtils.escapeJavaScript(json);
    }

    public static List<QuestionOption> getOptionsForQuestion(Long questionID) {
        List<QuestionOption> resultList = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            QuestionOptionMapper mapper = session.getMapper(QuestionOptionMapper.class);
            resultList = mapper.getOptionsForQuestion(questionID);
        } finally {
            session.close();
        }
        return resultList;
    }


    public static Question getQuestion(Long questionID) {
        Question question = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            QuestionMapper mapper = session.getMapper(QuestionMapper.class);
            question = mapper.getQuestion(questionID);
        } finally {
            session.close();
        }
        return question;
    }

    public static void answerOnQuestion(Answer answer) {
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            AnswerMapper mapper = session.getMapper(AnswerMapper.class);
            mapper.insert(answer);
            // System.out.println("record " + answer + "inserted successfully");// TODO tmp out
            session.commit();
        } finally {
            session.close();
        }
    }

    public static Answer getLastAnswerForUserQuestion(Long userID, Long questionID) {
        Answer lastAnswer = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            AnswerMapper mapper = session.getMapper(AnswerMapper.class);
            List<Answer> list = mapper.getAnswersForUserQuestion(userID, questionID);
            if(list != null && list.size() > 0){
                lastAnswer = list.get(0);
            }
        } finally {
            session.close();
        }
        return lastAnswer;
    }

    public static TestEventInfo getTestEventInfo(ProblemTask task, UserProfile userProfile) {
        TestEventInfo info = new TestEventInfo();
        List<Question> list = getQuestionsForTask(task.getId());
        info.setCountQuestions(String.valueOf(list.size()));
        java.util.Date startTime = getStartTimeForUserTask(userProfile.getUserId(), task.getId());
        info.setStartTime(startTime);
        int limitHours = task.getLimitTime() / SEC_IN_HOUR;
        int limitMin = (task.getLimitTime() % SEC_IN_HOUR) / SEC_IN_MIN;
        String limitFormatted = String.format("%d ч. %d мин.", limitHours, limitMin);
        info.setTimeLimit(limitFormatted);
        info.setKsiNumber(userProfile.getUserId());
        return info;
    }

    private static Date getStartTimeForUserTask(Long userId, Long taskId) {
        java.util.Date startTime = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            ProblemTaskMapper mapper = session.getMapper(ProblemTaskMapper.class);
            startTime = mapper.getStartTimeForUserTask(userId, taskId);
        } finally {
            session.close();
        }
        return startTime;
    }


    public static TaskResult getTaskResult(Long taskID, Long userID) {
        TaskResult result = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            TaskResultMapper mapper = session.getMapper(TaskResultMapper.class);
            result = mapper.getTaskResult(taskID);
            Map<Long, Answer> answerHash = new HashMap<>();
            AnswerMapper answerMapper = session.getMapper(AnswerMapper.class);
            for (Question question: result.getQuestions()){
                List<Answer> answerList = answerMapper.getAnswersForUserQuestion(userID, question.getId());
                if(answerList != null && answerList.size() > 0){
                    Answer lastAnswer = answerList.get(0);
                    answerHash.put(question.getId(), lastAnswer);
                }
            }
            result.setAnswers(answerHash);
        } finally {
            session.close();
        }
        return result;
    }

    public static void registerEvent(Event event) {
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            EventMapper mapper = session.getMapper(EventMapper.class);
            mapper.insert(event);
            // System.out.println("record " + event + "inserted successfully");// TODO tmp out
            session.commit();
        } finally {
            session.close();
        }
    }

    public static boolean isUserTaskExpired(Long userId, Long taskId) {
        Period taskLifePeriod = getLifePeriodForUserTask(userId, taskId);
        ProblemTask task = getTask(taskId);
        long taskLimitTimeInSecond = task.getLimitTime();
        long taskLifePeriodInSecond = (taskLifePeriod.getEnd().getTime() - taskLifePeriod.getStart().getTime()) / 1000;

        /*System.out.println("+++++++++++ life = " + taskLifePeriod); // TODO tmp out
        System.out.println("+++ limit sec = " + taskLimitTimeInSecond);
        System.out.println("+++ life Period sec = " + taskLifePeriodInSecond);*/

        if(taskLifePeriodInSecond > taskLimitTimeInSecond){
            return true;
        }
        return false;
    }

    private static Period getLifePeriodForUserTask(Long userId, Long taskId) {
        Period lifePeriod = null;
        SqlSession session = MyBatisConfig.getInstance().openSession();
        try {
            // do work
            ProblemTaskMapper mapper = session.getMapper(ProblemTaskMapper.class);
            lifePeriod = mapper.getLifePeriodForUserTask(userId, taskId);
        } finally {
            session.close();
        }
        return lifePeriod;
    }
}
