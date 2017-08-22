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
 * Question update form.
 *
 * Created by ishafigullin on 06.07.2017.
 */
public class QuestionUpdate {

    // The activation context
    @Property
    private Long questionId;

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
        return questionId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long questionId) {
        this.questionId = questionId;
    }

    // setupRender() is called by Tapestry right before it starts rendering the
    // page.

    void setupRender() {

        // We're doing this here instead of in onPrepareForRender() because
        // question is used outside the form.

        question = dictionaryDao.getQuestion(questionId);
        // Handle null question in the template.
    }

    // QuestionForm bubbles up the PREPARE_FOR_RENDER event during form render.

    void onPrepareForRender() {

        // If the form has errors then we're redisplaying after a redirect.
        // Form will restore your input values but it's up to us to restore
        // Hidden values.

        if (questionForm.getHasErrors()) {
            if (question != null) {
                // question.setVersion(versionFlash);
            }
        }
    }

    // QuestionForm bubbles up the PREPARE_FOR_SUBMIT event during form
    // submission.

    void onPrepareForSubmit() {
        // Get objects for the form fields to overlay.
        question = dictionaryDao.getQuestion(questionId);

        if (question == null) {
            question = new Question(questionId);
            // Unfortunately this form error message will never be displayed
            // because we can't do <t:if test="user>
            // INSIDE the BeanEditForm.
            questionForm
                    .recordError("Question has been deleted by another process.");
        }
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
            dictionaryDao.changeQuestion(question);
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
        alertManager.success("Вопрос №" + question.getSeqNo() + " сохранен успешно!");
        Link returnLink = pageRenderLinkSource.createPageRenderLinkWithContext(Questions.class, question.getProblemId());
        return returnLink;
    }

    void onFailure() {
        // versionFlash = question.getVersion();
    }
}
