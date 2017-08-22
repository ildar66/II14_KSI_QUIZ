package com.cboss.ksi.admin.pages.question;

import com.cboss.ksi.admin.pages.dictionary.Questions;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Question create form.
 *
 * Created by ishafigullin on 07.07.2017.
 */
public class QuestionCreate {

    // The activation context
    @Property
    private Long problemId;

    // Screen fields

    @Property
    private Question question;

    // Work fields

    // This carries version through the redirect that follows a server-side
    // validation failure.
    /*@Persist(PersistenceConstants.FLASH)
    private Integer versionFlash;*/

    // Other pages

    // Generally useful bits and pieces

    @InjectComponent
    private BeanEditForm questionForm;
    @Inject
    private DictionaryDao dictionaryDao;
    @Inject
    private AlertManager alertManager;
    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    // The code

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return problemId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long problemId) {
        this.problemId = problemId;
    }

    // The code

    // QuestionForm bubbles up the PREPARE event when it is rendered or submitted

    void onPrepare() throws Exception {
        // Instantiate a Question for the form data to overlay.
        question = new Question(null);
        question.setProblemId(problemId);
    }

    // QuestionForm bubbles up the VALIDATE event when it is submitted

    void onValidateFromQuestionForm() {

        if ( question.getSeqNo() == null) {
            // login.recordError(seqNo, "Try with user: users@tapestry.apache.org");
            questionForm.recordError("Необходимо заполнить поле 'Очередь'");
        }
        if ( question.getFromDate() == null || question.getToDate() == null) {
            questionForm.recordError("Необходимо заполнить поля 'Период дат валидности'");
        }

        if ( question.getOptionsType() == null) {
            questionForm.recordError("Необходимо заполнить поля 'Тип опций'");
        }

        if (question.getUserOption() && question.getUserOptionLabel() == null) {
            questionForm.recordError("Необходимо заполнить поля 'Текстовый вариант участника'");
        }

        // TODO: add validation for all fielsd
        if (questionForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            dictionaryDao.createQuestion(question);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
            // questionForm.recordError(ExceptionUtil.getRootCauseMessage(e));
             questionForm.recordError("Exception=" + e);
        }
    }

    // QuestionForm bubbles up SUCCESS or FAILURE when it is submitted, depending
    // on whether VALIDATE records an error

    Link onSuccess() {
        alertManager.success("Вопрос №" + question.getSeqNo() + " успешно создан!");
        Link returnLink = pageRenderLinkSource.createPageRenderLinkWithContext(Questions.class, question.getProblemId());
        return returnLink;
    }
}
