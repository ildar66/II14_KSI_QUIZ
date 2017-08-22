package com.cboss.ksi.admin.pages.problem;

import com.cboss.ksi.admin.pages.dictionary.Problems;
import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * Created by ishafigullin on 28.06.2017.
 */
public class ProblemUpdate {

    // The activation context
    @Property
    private Long problemId;

    // Screen fields

    @Property
    private Problem problem;

    // Work fields

    // This carries version through the redirect that follows a server-side
    // validation failure.
    /*@Persist(PersistenceConstants.FLASH)
    private Integer versionFlash;*/

    // Other pages

    @InjectPage
    private Problems indexPage;

    // Generally useful bits and pieces

    @InjectComponent
    private BeanEditForm problemForm;
    @Inject
    private DictionaryDao dictionaryDao;
    @Inject
    private AlertManager alertManager;

    // The code

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return problemId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long problemId) {
        this.problemId = problemId;
    }

    // setupRender() is called by Tapestry right before it starts rendering the
    // page.

    void setupRender() {

        // We're doing this here instead of in onPrepareForRender() because
        // problem is used outside the form.

        problem = dictionaryDao.getProblem(problemId);
        // Handle null problem in the template.
    }

    // ProblemForm bubbles up the PREPARE_FOR_RENDER event during form render.

    void onPrepareForRender() {

        // If the form has errors then we're redisplaying after a redirect.
        // Form will restore your input values but it's up to us to restore
        // Hidden values.

        if (problemForm.getHasErrors()) {
            if (problem != null) {
                // problem.setVersion(versionFlash);
            }
        }
    }

    // ProblemForm bubbles up the PREPARE_FOR_SUBMIT event during form
    // submission.

    void onPrepareForSubmit() {
        // Get objects for the form fields to overlay.
        problem = dictionaryDao.getProblem(problemId);

        if (problem == null) {
            problem = new Problem(problemId);
            // Unfortunately this form error message will never be displayed
            // because we can't do <t:if test="user>
            // INSIDE the BeanEditForm.
            problemForm
                    .recordError("Problem has been deleted by another process.");
        }
    }

    // ProblemForm bubbles up the VALIDATE event when it is submitted

    void onValidateFromProblemForm() {

        if ( problem.getSeqNo() == null) {
            // login.recordError(seqNo, "Try with user: users@tapestry.apache.org");
            problemForm.recordError("Необходимо заполнить поле 'Очередь'");
        }
        if ( problem.getRound() == null) {
            problemForm.recordError("Необходимо заполнить поле 'Раунд'");
        }
        if ( problem.getStatus() == null) {
            problemForm.recordError("Необходимо заполнить поле 'Статус'");
        }
        if ( problem.getFromDate() == null || problem.getToDate() == null) {
            problemForm.recordError("Необходимо заполнить поля 'Период дат валидности'");
        }
        if ( problem.getLimitTime() == null) {
            problemForm.recordError("Необходимо заполнить поле 'Лимит'");
        }

        // TODO: add validation for all fielsd
        if (problemForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            dictionaryDao.changeProblem(problem);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
            // problemForm.recordError(ExceptionUtil.getRootCauseMessage(e));
             problemForm.recordError("Exception=" + e);
        }
    }

    // ProblemForm bubbles up SUCCESS or FAILURE when it is submitted, depending
    // on whether VALIDATE records an error

    Object onSuccess() {
        alertManager.success("Задание №" + problem.getSeqNo() + " сохранено успешно!");
        return indexPage;
    }

    void onFailure() {
        // versionFlash = problem.getVersion();
    }
}
