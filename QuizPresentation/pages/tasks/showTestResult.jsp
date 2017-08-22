<%@ page import="ru.cboss.contest.modules.tasks.beans.QuestionOption" %>
<%@ page import="ru.cboss.contest.modules.tasks.beans.Question" %>
<%--
  Created by IntelliJ IDEA.
  User: ishafigullin
  Date: 14.07.2017
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ShowTestResult</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/quiz/Task.css" />
</head>
<body>
    <jsp:useBean id='form' class='ru.cboss.contest.modules.tasks.forms.ShowTestResultForm' scope='request'/>
    <c:set var="result" value="<%=form.getResult()%>"/>

    Результаты теста:
    <%-- Номер и тело задания --%>
    <p>Задание № ${result.seqNo}</p>
    <p>${result.body}</p>

    <c:forEach items="${result.questions}" var="question">
        <% Question question = (Question)pageContext.getAttribute("question"); %>
        <%-- Номер и тело вопроса --%>
    <details open>
        <summary>Вопрос № ${question.seqNo}</summary>
        <fieldset class="question-group" data-role="controlgroup">
            <div class="question-body"><p>${question.body}</p></div>
            <c:if test="${not empty question.bodyImg}">
                <img class="small_media" src="/images/quiz/${question.bodyImg}"/></c:if>
            <%-- Список опций к вопросу --%>
            <c:if test="${question.options != null}">
                <c:choose>
                    <c:when test="${question.optionsType == 'UNIQUE'}">
                        <c:forEach items="${question.options}" var="option">
                            <% QuestionOption opt = (QuestionOption)pageContext.getAttribute("option");
                               String isChecked = form.optionSelectionAttr(opt.getQuestionId(), opt.getId()); %>
                            <label>
                                <input type='radio' name='option_${question.id}' value='${option.id}' <%=isChecked%>>
                                    <c:if test="${not empty option.bodyImg}">
                                        <img class="tiny-media" src="/images/quiz/${option.bodyImg}"/></c:if>
                                    <c:if test="${not empty option.body}">${option.body}</c:if><br>
                            </label>
                        </c:forEach>
                        <c:if test="${question.userOption}">
                            <div class="user-option-text">
                                <label>
                                    <input type="radio" value="0" name="option_${question.id}"
                                      <%= form.optionSelectionAttr(question.getId(), 0L) %> onclick="UserOptionWarning(this)" />
                                        ${question.userOptionLabel}:
                                </label>
                                <input type="text" name='userOptionValue' value='<%=form.getUserOptionValue(question.getId())%>'/>
                            </div>
                        </c:if>
                    </c:when>
                    <c:when test="${question.optionsType == 'MULTIPLE'}">
                        <c:forEach items="${question.options}" var="option">
                            <% QuestionOption opt = (QuestionOption)pageContext.getAttribute("option");
                               String isChecked = form.categorySelectionAttr(opt.getQuestionId(), opt.getId()); %>
                            <label>
                                <input type='checkbox' name='categories_${question.id}' value='${option.id}' <%=isChecked%>>
                                    <c:if test="${not empty option.bodyImg}">
                                        <img class="tiny-media" src="/images/quiz/${option.bodyImg}"/></c:if>
                                    <c:if test="${not empty option.body}">${option.body}</c:if><br>
                            </label>
                        </c:forEach>
                        <c:if test="${question.userOption}">
                            <div class="user-option-text">
                                <label>
                                    <input type="checkbox" name='categories_${question.id}' value="0"
                                      <%= form.categorySelectionAttr(question.getId(), 0L) %> onclick="UserOptionWarning(this)" />
                                        ${question.userOptionLabel}:
                                </label>
                                <input type="text" name='userOptionValue' value='<%=form.getUserOptionValue(question.getId())%>'/>
                            </div>
                        </c:if>
                    </c:when>
                    <c:when test="${question.optionsType == 'NONE'}">
                        question has OptionsType.NONE
                    </c:when>
                </c:choose>
            </c:if>

            <div data-role="fieldcontain">
                <label class="confidence">Уверенность в ответе:</label>
                <input type="range" name="confidence" min="1" max="100" value="<%= form.getAnswerConfidence(question.getId()) %>"
                       data-highlight="true" data-track-theme="b" />
            </div>
            <div class="user-remarks">
                <label>Ваше пояснение к ответу:</label><br/>
                <textarea name='comment'><%= form.getAnswerComment(question.getId()) %></textarea>
            </div>
        </fieldset>
    </details>
    </c:forEach>

    <p><a href="/tasksList"><span class="main-button">Вернуться к списоку заданий</span></a></p>
</body>
</html>
