package ru.cboss.core.web.main.actions;

import java.util.Hashtable;
/**
 * Factory for {@link Action}
 *
 * Created by ishafigullin on 06.07.2017.
 */
public class ActionFactory { 
   private Hashtable actions = new Hashtable();

   // This method is called by the action servlet

   public Action getAction(String classname, ClassLoader loader)
           throws ClassNotFoundException, IllegalAccessException, InstantiationException {

       // System.out.println("++++++++++++++++++++ Action classname = " + classname); // TODO tmp

       Action action = (Action)actions.get(classname);

      if(action == null) {
         Class klass = loader.loadClass(classname);
         action = (Action)klass.newInstance();
         actions.put(classname, action);
      }

       // System.out.println("++++++++++++++++++++ Action Class = " + action); // TODO tmp

      return action;
   }
}
