<%--
  Created by IntelliJ IDEA.
  User: ishafigullin
  Date: 11.07.2017
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<p>Режим в разработке !!!</p>
<%
  java.util.Enumeration e = request.getParameterNames();
  while(e.hasMoreElements()) {
    String name = (String)e.nextElement();
    String[] values = request.getParameterValues(name);

    for(int i=0; i < values.length; ++i) {
      String next = values[i];
      if(i == 0) { %>
          <b><%= name %>:</b> <%= next %>
<%    }
      else { %>
          , <%= next %>
<%    }
    } %>
  <br/>
<% }
%>
