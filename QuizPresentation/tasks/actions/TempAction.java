package ru.cboss.contest.modules.tasks.actions;

import ru.cboss.contest.modules.tasks.beans.ProblemTask;
import ru.cboss.contest.util.QuizApiUtil;
import ru.cboss.core.web.main.actions.Action;
import ru.cboss.core.web.main.actions.ActionRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ishafigullin.
 *
 * TODO example actions.
 */
//@Component
public class TempAction implements Action{
/*    @Autowired
    JdbcTemplate jdbcTemplate;*/

    @Override
    public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        //return new ActionRouter("/index.jsp");
        //System.out.println("++++++++++++++++jdbcTemplate= " + jdbcTemplate);

        List<ProblemTask> list = QuizApiUtil.getTaskList(3706L);
        System.out.println("++++++++ Task from myBatis annotated mapping: task= " + list); // TODO temp out
        return new ActionRouter("/main.jsp");
    }
}
