package ru.cboss.core.web.main.actions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application-specific actions implement this interface.
 *
 * Created by ishafigullin on 06.07.2017.
 */
public interface Action {

   public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res)
            throws java.io.IOException, javax.servlet.ServletException;

}
