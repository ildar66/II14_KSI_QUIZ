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
public class ProblemCreate {
    // Screen fields

    @Property
    private Problem problem;

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

    // ProblemForm bubbles up the PREPARE event when it is rendered or submitted

    void onPrepare() throws Exception {
        // Instantiate a Problem for the form data to overlay.
        problem = new Problem(null);
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
             dictionaryDao.createProblem(problem);
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
        alertManager.success("Задание №" + problem.getSeqNo() + " создано успешно!");
        return indexPage;
    }
}
