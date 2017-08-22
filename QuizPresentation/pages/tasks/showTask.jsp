<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.cboss.contest.modules.tasks.beans.QuestionOption" %>
<%@ page import="ru.cboss.contest.modules.tasks.beans.OptionsType" %>
<%--
  Created by IntelliJ IDEA.
  User: ishafigullin
  Date: 07.07.2017
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>show Task for user</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/quiz/Task.css" />

    <script src="/js/quiz/TaskScripts.js"></script>
</head>
<body>
    <jsp:useBean id='form' class='ru.cboss.contest.modules.tasks.forms.ShowTaskForm' scope='request'>
        <jsp:setProperty name='form' property='*'/>
    </jsp:useBean>

    <c:set var="optionList" value="<%=form.getOptionList()%>"/>
    <c:set var="question" value="<%=form.getCurrentQuestion()%>"/>
    <c:set var="task" value="<%=form.getCurrentTask()%>"/>

    <%--<form action='/pages/tasks/showRequestParameters.jsp' method='post'>--%>
    <form action='ru.cboss.contest.modules.tasks.actions.QuestionAnswerAction.do' method='post'>
        <input type="hidden" name="questionID" value="${question.id}">
        <%-- Номер и тело задания --%>
        <details open>
            <summary class="page-headline">Задание ${task.seqNo}</summary>
            <%-- Время отведенное на задание и кол-во вопросов --%>
            <c:set var="info" value="<%=form.getInfo()%>"/>
            <div class="time-limit">
                <div>Время, отведенное на задание: ${info.timeLimit}</div>
                <div>Время начала: <fmt:formatDate pattern="dd MMM HH:mm" value="${info.startTime}" /> Вопросов: ${info.countQuestions}</div>
                <div>CH: ${info.ksiNumber}</div>
            </div>
            <p>${task.body}</p>
            <c:if test="${not empty task.bodyImg}">
                <img class="small_media" src="/images/quiz/${task.bodyImg}"/></c:if>
        </details>
        <%-- Номер и тело вопроса --%>
        <details open>
            <summary>Вопрос ${question.seqNo}</summary>
            <fieldset class="question-group" data-role="controlgroup">
                <div class="question-body"><p>${question.body}</p></div>
                <c:if test="${not empty question.bodyImg}">
                    <img class="small_media" src="/images/quiz/${question.bodyImg}"/></c:if>
                <%-- Список опций к вопросу --%>
                <div class="options-list">
                    <c:if test="${optionList != null}">
                    <c:choose>
                        <c:when test="<%= form.getCurrentQuestion().getOptionsType() == OptionsType.UNIQUE %>">
                            <div class="options-list" style="margin-bottom:0">
                                <c:forEach items="${optionList}" var="option">
                                    <label>
                                        <input type='radio' name='option' value='${option.id}'
                                            <%= form.optionSelectionAttr( "" + ((QuestionOption)pageContext.getAttribute("option")).getId() ) %>>
                                            <c:if test="${not empty option.bodyImg}">
                                                <img class="tiny-media" src="/images/quiz/${option.bodyImg}"/></c:if>
                                            <c:if test="${not empty option.body}">${option.body}</c:if><br>
                                    </label>
                                </c:forEach>
                            </div>
                            <c:if test="<%= form.getCurrentQuestion().isUserOption() %>">
                                <div class="user-option-text">
                                    <label>
                                        <input type="radio" value="0" name="option" <%= form.optionSelectionAttr("0") %>
                                               onclick="UserOptionWarning(this)" />
                                        <%= form.getCurrentQuestion().getUserOptionLabel() %>:
                                    </label>
                                    <input type="text" name='userOptionValue' value='<%=form.getUserOptionValue()%>'/>
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="<%= form.getCurrentQuestion().getOptionsType() == OptionsType.MULTIPLE %>">
                            <div class="options-list" id="q1-options">
                                <c:forEach items="${optionList}" var="option">
                                    <label>
                                        <input type='checkbox' name='categories' value='${option.id}'
                                            <%= form.categorySelectionAttr( "" + ((QuestionOption)pageContext.getAttribute("option")).getId() ) %>>
                                            <c:if test="${not empty option.bodyImg}">
                                                <img class="tiny-media" src="/images/quiz/${option.bodyImg}"/></c:if>
                                            <c:if test="${not empty option.body}">${option.body}</c:if><br>
                                    </label>
                                </c:forEach>
                            </div>
                            <c:if test="<%= form.getCurrentQuestion().isUserOption() %>">
                                <div class="user-option-text">
                                    <label>
                                        <input type="checkbox" name='categories' value="0" <%= form.categorySelectionAttr("0") %>
                                               onclick="UserOptionWarning(this)" />
                                        <%= form.getCurrentQuestion().getUserOptionLabel() %>:
                                    </label>
                                    <input type="text" name='userOptionValue' value='<%=form.getUserOptionValue()%>'/>
                                </div>
                            </c:if>
                        </c:when>
                        <c:when test="<%= form.getCurrentQuestion().getOptionsType() == OptionsType.NONE %>">
                            question has OptionsType.NONE
                        </c:when>
                    </c:choose>
                    </c:if>
                </div>
                <div data-role="fieldcontain">
                    <label class="confidence">Уверенность в ответе:</label>
                    <input type="range" name="confidence" min="1" max="100" value="<%= form.getConfidence() %>" data-highlight="true" data-track-theme="b" />
                </div>
                <div class="user-remarks">
                    <label>Ваше пояснение к ответу:</label><br/>
                    <textarea name='comment'><%= form.getComment() %></textarea>
                </div>
            </fieldset>
        </details>
        <p>
            <input type='submit' value='Готово'/>
            <input type='button' value='Отказаться' onclick="RefusedTask('${task.id}')"/>
        </p>
    </form>

    <p><a href="/tasksList"><span class="main-button">Вернуться к списоку заданий</span></a></p>
</body>
</html>
