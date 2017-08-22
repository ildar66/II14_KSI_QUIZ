package ru.cboss.contest.modules.tasks.actions;

import ru.cboss.contest.modules.tasks.beans.TaskResult;
import ru.cboss.contest.modules.tasks.forms.ShowTestResultForm;
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
 * Show test result for KSI user Action.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public class ShowTestResultAction implements Action {
    @Override
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        validateInputParams(req);

        Long taskID = Long.valueOf(req.getParameter("task"));
        UserProfile userProfile = (UserProfile) req.getSession().getAttribute( "userProfile" );
        TaskResult result = ProblemTaskUtil.getTaskResult(taskID, userProfile.getUserId());

        ShowTestResultForm form = (ShowTestResultForm)req.getAttribute("form");
        if( form == null) {
            form = new ShowTestResultForm();
            req.setAttribute("form", form);
        }
        form.setResult(result);

        return new ActionRouter("/pages/tasks/showTestResult.jsp");
    }

    private void validateInputParams(HttpServletRequest req) throws ServletException {
        if(req.getParameter("task") == null) {
            throw new ServletException("parameter task is NULL");
        }
    }
}
