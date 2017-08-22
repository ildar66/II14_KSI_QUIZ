package ru.cboss.contest.modules.tasks.actions;

import com.cbossgroup.ksi.data.EventTypeEnum;
import com.cbossgroup.ksi.data.ObjectTypeEnum;
import ru.cboss.contest.modules.tasks.beans.Event;
import ru.cboss.contest.modules.tasks.beans.ProblemTask;
import ru.cboss.contest.modules.tasks.beans.Question;
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
import java.util.List;

/**
 * Show Task(Problem) Action.
 *
 * Created by ishafigullin on 06.07.2017.
 */
public class ShowTaskAction implements Action{
    @Override
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        validateInputParams(req);

        Long taskID = Long.valueOf(req.getParameter("task"));
        UserProfile userProfile = (UserProfile) req.getSession().getAttribute( "userProfile" );

        boolean isTaskExpired = ProblemTaskUtil.isUserTaskExpired(userProfile.getUserId(), taskID);
        if(!isTaskExpired){
            Question question = getFirstQuestionForTask(taskID);

            ShowTaskForm form = (ShowTaskForm)req.getAttribute("form");
            if( form == null) {
                form = new ShowTaskForm();
                req.setAttribute("form", form);
            }
            form.init(userProfile, question);

            registerOpenProblemEvent(userProfile, question);
            return new ActionRouter("/pages/tasks/showTask.jsp");
        } else {
            registerExpiredProblemEvent(userProfile.getUserId(), taskID);
            return new ActionRouter("/tasksList");
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

    private void registerOpenProblemEvent(UserProfile userProfile, Question question) {
        Event event = new Event();
        event.setEventType(EventTypeEnum.OPEN_PROBLEM);
        event.setObjectType(ObjectTypeEnum.PROBLEM);
        event.setObjId(question.getProblemId());
        event.setUserId(userProfile.getUserId());
        event.setDsc(ProblemTask.State.OPEN.toString());
        ProblemTaskUtil.registerEvent(event);
    }

    private Question getFirstQuestionForTask(Long taskID) {
        Question question = null;
        List<Question> list = ProblemTaskUtil.getQuestionsForTask(taskID);
        if(list != null && list.size() > 0){
            question = list.get(0);
        }
        return question;
    }

    private void validateInputParams(HttpServletRequest req) throws ServletException {
        if(req.getParameter("task") == null) {
            throw new ServletException("parameter task is NULL");
        }
    }
}
