package ru.cboss.core.web.main;

import ru.cboss.core.web.main.actions.Action;
import ru.cboss.core.web.main.actions.ActionFactory;
import ru.cboss.core.web.main.actions.ActionRouter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Front controller servlet.
 *
 * Created by ishafigullin on 06.07.2017.
 */

public class ActionServlet extends HttpServlet {
    private ActionFactory factory = new ActionFactory();

    public void init(ServletConfig config) throws ServletException{
        super.init(config);

      /*ResourceBundle bundle = null;

      try {
         bundle = ResourceBundle.getBundle(
                  config.getInitParameter("action-mappings"));
      }
      catch(MissingResourceException e) {
         throw new ServletException(e);
      }
      getServletContext().setAttribute("action-mappings", bundle);*/
    }

    public void service(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException {
      /*try {
         String actionClass = getActionClass(req);
         Action action = factory.getAction(actionClass,
                                 getClass().getClassLoader());
         ActionRouter router = action.perform(this,req,res);
         router.route(this, req, res);
      }
      catch(Exception e) {
         throw new ServletException(e);
      }*/

        try {
            Action action = factory.getAction(getClassname(req), getClass().getClassLoader());
            res.setContentType("text/html; charset=UTF-8");
            req.setCharacterEncoding ("UTF-8");
            ActionRouter router = action.perform(this,req,res);
            router.route(this, req, res);
        }
        catch(Exception e) {
            throw new ServletException(e);
        }
    }

    private String getClassname(HttpServletRequest req) {
        String path = req.getServletPath();
        int slash = path.lastIndexOf("/"),
                period = path.lastIndexOf(".");

        if(period > 0 && period > slash)
            path = path.substring(slash+1, period);

        // System.out.println("++++++++++++++++++ Action = " + path); // TODO tmp
        return path;
    }

/*   private String getActionClass(HttpServletRequest req) {
      ResourceBundle bundle = (ResourceBundle)getServletContext().
                              getAttribute("action-mappings");
      return (String)bundle.getObject(getActionKey(req));
   }

   private String getActionKey(HttpServletRequest req) {
      String path = req.getServletPath();
      int slash = path.lastIndexOf("/"),
          period = path.lastIndexOf(".");

      if(period > 0 && period > slash)
       path = path.substring(slash+1, period);

      return path;
   }*/
}

