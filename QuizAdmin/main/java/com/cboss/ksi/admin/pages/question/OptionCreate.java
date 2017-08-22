package com.cboss.ksi.admin.pages.question;

import com.cboss.ksi.admin.pages.dictionary.QuestionOptions;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Question options create page.
 *
 * Created by ishafigullin on 11.07.2017.
 */
public class OptionCreate {

    // The activation context
    @Property
    private Long questionId;

    // Screen fields

    @Property
    private QuestionOption option;

    // Generally useful bits and pieces

    @InjectComponent
    private BeanEditForm optionForm;
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

    // OptionForm bubbles up the PREPARE event when it is rendered or submitted

    void onPrepare() throws Exception {
        // Instantiate a Question for the form data to overlay.
        option = new QuestionOption(null);
        option.setQuestionId(questionId);
    }

    // OptionForm bubbles up the VALIDATE event when it is submitted

    void onValidateFromOptionForm() {

        if ( option.getSeqNo() == null) {
            optionForm.recordError("Необходимо заполнить поле 'Очередь'");
        }
        if ( option.getFromDate() == null || option.getToDate() == null) {
            optionForm.recordError("Необходимо заполнить поля 'Период дат валидности'");
        }
        if ( option.getBodyImg() == null && option.getBody() == null) {
            optionForm.recordError("Необходимо заполнить поле 'Тело опции' или 'URL картинки'");
        }

        // TODO: add validation for all fielsd
        if (optionForm.getHasErrors()) {
            // We get here only if a server-side validator detected an error.
            return;
        }

        try {
            dictionaryDao.createQuestionOption(option);
        } catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a
            // user-friendly message.
            // optionForm.recordError(ExceptionUtil.getRootCauseMessage(e));
            optionForm.recordError("Exception=" + e);
        }
    }

    // QuestionForm bubbles up SUCCESS or FAILURE when it is submitted, depending
    // on whether VALIDATE records an error

    Link onSuccess() {
        alertManager.success("Опция №" + option.getSeqNo() + " успешно создана!");
        Link returnLink = pageRenderLinkSource.createPageRenderLinkWithContext(QuestionOptions.class, option.getQuestionId());
        return returnLink;
    }

}
