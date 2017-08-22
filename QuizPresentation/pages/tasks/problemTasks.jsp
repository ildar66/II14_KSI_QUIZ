<%@ page import="ru.cboss.core.user.UserProfile" %>
<%@ page import="ru.cboss.contest.util.ProblemTaskUtil" %>
<%@ page import="ru.cboss.contest.modules.tasks.beans.ProblemTask" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="taskList"
       value="<%=ProblemTaskUtil.getTaskList(((UserProfile) session.getAttribute(\"userProfile\")).getUserId())%>"/>

<%-- Список доступных заданий --%>
<c:if test="${taskList != null}">
    <br/><br/>Список доступных заданий:<br/>
    <table>
        <c:forEach items="${taskList}" var="task">
            <tr>
                <td>
                    <%--Task state: <c:out value="${task.state}"/>--%>
                    <c:choose>
                        <%--<c:when test="${f:containsLong(taskStatuses, 1)}">--%>
                        <c:when test="${task.state == 'OPEN'}">
                            <a href="ru.cboss.contest.modules.tasks.actions.ShowTaskAction.do?task=${task.id}">
                                <span style="display: block;" class="${task.styleClass}">
                                    Задание №${task.id} выполняется
                                </span>
                             </a>
                        </c:when>
                        <%--<c:when test="${ empty sessionScope.T1 }">--%>
                        <c:when test="${task.state == 'NEW'}">
                            <c:set var="foJson">
                                <%--<%=EventHelper.checkTaskOpen(request, ((ProblemTask)pageContext.getAttribute("task")).getId())%>--%>
                                <%=ProblemTaskUtil.checkTaskOpen(((ProblemTask)pageContext.getAttribute("task")).getId())%>
                            </c:set>
                            <a href="#" onclick="goTask('${foJson}'); return false;">
                            <%--<a href="#" onclick="alert('Режим в разработке!!!'); return false;">--%>
                                <span style="display: block;" class="${task.styleClass}">
                                    Выполнить задание №${task.id}
								</span>
                            </a>
                        </c:when>
                        <c:when test="${task.state == 'RATED'}">
                            <span style="display: block;" class="${task.styleClass}">
                                Задание №${task.id} оценено
                            </span>
                        </c:when>
                        <c:when test="${task.state == 'SOLVE'}">
                            <a href="ru.cboss.contest.modules.tasks.actions.ShowTestResultAction.do?task=${task.id}">
                                <span style="display: block;" class="${task.styleClass}">
                                    Задание №${task.id} выполнено
                                </span>
                            </a>
                        </c:when>
                        <c:when test="${task.state == 'EXPIRED'}">
                            <span style="display: block;" class="${task.styleClass}">
                                Задание №${task.id} просрочено
                            </span>
                        </c:when>
                        <c:when test="${task.state == 'REFUSED'}">
                            <span style="display: block;" class="${task.styleClass}">
                                Задание №${task.id}, отказ
                            </span>
                        </c:when>
                        <%--<c:otherwise>
                            <span style="display: block;" class="main-button-no-active">
                                Задание №${task.id} уже выполнено
                            </span>
                        </c:otherwise>--%>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
