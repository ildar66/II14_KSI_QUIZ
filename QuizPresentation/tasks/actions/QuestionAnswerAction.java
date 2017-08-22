package ru.cboss.contest.modules.tasks.actions;

import com.cbossgroup.ksi.data.EventTypeEnum;
import com.cbossgroup.ksi.data.ObjectTypeEnum;
import ru.cboss.contest.modules.tasks.beans.*;
import ru.cboss.contest.modules.tasks.forms.ShowTaskForm;
import ru.cboss.contest.util.ProblemTaskUtil;
import ru.cboss.core.user.UserProfile;
import ru.cboss.core.web.main.actions.Action;
import ru.cboss.core.web.main.actions.ActionRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Put answer for current question Task(Problem) Action.
 *
 * Created by ishafigullin on 13.07.2017.
 */
public class QuestionAnswerAction implements Action {

    @Override
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        validateInputParams(req);

        Long questionID = Long.valueOf(req.getParameter("questionID"));
        UserProfile userProfile = (UserProfile) req.getSession().getAttribute( "userProfile" );
        Question question = ProblemTaskUtil.getQuestion(questionID);

        boolean isTaskExpired = ProblemTaskUtil.isUserTaskExpired(userProfile.getUserId(), question.getProblemId());
        if(isTaskExpired) {
            registerExpiredProblemEvent(userProfile.getUserId(), question.getProblemId());
            return new ActionRouter("/tasksList");
        } else {
            return getActionRouterForSaveAnswerProcess(req, userProfile, question);
        }
    }

    private ActionRouter getActionRouterForSaveAnswerProcess(HttpServletRequest req, UserProfile userProfile, Question question) {
        saveAnswerForQuestion(req, question, userProfile);

        Question nextQuestion = getNextQuestion(question);
        if(nextQuestion != null) {
            ShowTaskForm form = (ShowTaskForm)req.getAttribute("form");
            if( form == null) {
                form = new ShowTaskForm();
                req.setAttribute("form", form);
            }

            form.init(userProfile, nextQuestion);
            return new ActionRouter("/pages/tasks/showTask.jsp");
        } else {
            registerSolveProblemEvent(userProfile, question);
            return new ActionRouter("/ru.cboss.contest.modules.tasks.actions.ShowTestResultAction.do?task=" + question.getProblemId());
        }
    }

    private void registerExpiredProblemEvent(Long userID, Long taskID) {
        Event event = new Event();
        event.setEventType(EventTypeEnum.EXPIRED_PROBLEM);
        event.setObjectType(ObjectTypeEnum.PROBLEM);
        event.setObjId(taskID);
        event.setUserId(userID);
        event.setDsc(ProblemTask.State.EXPIRED.toString());
        ProblemTaskUtil.registerEvent(event);
    }

    private void registerSolveProblemEvent(UserProfile userProfile, Question question) {
        Event event = new Event();
        event.setEventType(EventTypeEnum.SOLVE_PROBLEM);
        event.setObjectType(ObjectTypeEnum.PROBLEM);
        event.setObjId(question.getProblemId());
        event.setUserId(userProfile.getUserId());
        event.setDsc(ProblemTask.State.SOLVE.toString());
        ProblemTaskUtil.registerEvent(event);
    }

    private Question getNextQuestion(Question lastQuestion) {
        Question nextQuestion = null;
        List<Question> list = ProblemTaskUtil.getQuestionsForTask(lastQuestion.getProblemId());
        int indexNextQuestion = 0;
        for (Question current: list) {
            indexNextQuestion++;
            if(current.getId().equals(lastQuestion.getId())){
                break;
            }
        }
        if( indexNextQuestion < list.size()){
            nextQuestion = list.get(indexNextQuestion);
        }
        // System.out.println("indexNextQuestion=" + indexNextQuestion + "; list.size()=" + list.size());//TODO temp out
        return nextQuestion;
    }

    private void validateInputParams(HttpServletRequest req) throws ServletException {
        if(req.getParameter("questionID") == null) {
            throw new ServletException("parameter questionID is NULL");
        }
    }

    private void saveAnswerForQuestion(HttpServletRequest req, Question question, UserProfile userProfile) {
        Answer answer = new Answer();
        answer.setQuestionId(question.getId());
        answer.setDsc(req.getParameter("comment"));
        String userOptionValue = req.getParameter("userOptionValue");
        answer.setTxt(userOptionValue != null ? userOptionValue : "");
        answer.setConfidence(Integer.valueOf(req.getParameter("confidence")));
        if(question.getOptionsType() == OptionsType.MULTIPLE ){
            String[] categories = req.getParameterValues("categories");
            answer.setBody(Arrays.toString(categories));
        } else if (question.getOptionsType() == OptionsType.UNIQUE){
            String option = req.getParameter("option");
            answer.setBody(option != null ? option : "");
        } else {
            answer.setBody(OptionsType.NONE.toString());
        }
        answer.setUserId(userProfile.getUserId());
        ProblemTaskUtil.answerOnQuestion(answer);
    }
}
