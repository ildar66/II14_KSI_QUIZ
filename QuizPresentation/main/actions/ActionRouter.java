package ru.cboss.core.web.main.actions;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Action routers are immutable.
 *
 * Created by ishafigullin on 06.07.2017.
 */
public class ActionRouter {
    private final String url;
    private final boolean isForward;

    public ActionRouter(String url) {
        this(url, true); // forward by default
    }

    public ActionRouter(String url, boolean isForward) {
        this.url = url;
        this.isForward = isForward;
    }

    // This method is called by the action servlet
    public void route(GenericServlet servlet, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, java.io.IOException {

        if(isForward) {
            servlet.getServletContext().getRequestDispatcher(res.encodeURL(url)).forward(req, res);
        }
        else {
            res.sendRedirect(res.encodeRedirectURL(url));
        }
    }
}
