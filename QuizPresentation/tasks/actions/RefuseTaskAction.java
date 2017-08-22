package ru.cboss.contest.modules.tasks.actions;

import com.cbossgroup.ksi.data.EventTypeEnum;
import com.cbossgroup.ksi.data.ObjectTypeEnum;
import ru.cboss.contest.modules.tasks.beans.Event;
import ru.cboss.contest.modules.tasks.beans.ProblemTask;
import ru.cboss.contest.util.ProblemTaskUtil;
import ru.cboss.core.user.UserProfile;
import ru.cboss.core.web.main.actions.Action;
import ru.cboss.core.web.main.actions.ActionRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Refuse Task(Problem) Action.
 *
 * Created by ishafigullin on 16.08.2017.
 */
public class RefuseTaskAction  implements Action {

    @Override
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        validateInputParams(req);

        Long taskID = Long.valueOf(req.getParameter("task"));
        UserProfile userProfile = (UserProfile) req.getSession().getAttribute( "userProfile" );

        registerRefusedProblemEvent(userProfile.getUserId(), taskID);

        return new ActionRouter("/tasksList");
    }

    private void registerRefusedProblemEvent(Long userID, Long taskID) {
        Event event = new Event();
        event.setEventType(EventTypeEnum.REFUSE_PROBLEM);
        event.setObjectType(ObjectTypeEnum.PROBLEM);
        event.setObjId(taskID);
        event.setUserId(userID);
        event.setDsc(ProblemTask.State.REFUSED.toString());
        ProblemTaskUtil.registerEvent(event);
    }

    private void validateInputParams(HttpServletRequest req) throws ServletException {
        if(req.getParameter("task") == null) {
            throw new ServletException("parameter task is NULL");
        }
    }
}
