package com.cboss.ksi.admin.pages.question;

import com.cboss.ksi.admin.pages.dictionary.QuestionOptions;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

/**
 * Question options update page.
 *
 * Created by ishafigullin on 12.07.2017.
 */
public class OptionUpdate {
    @PageActivationContext
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
            dictionaryDao.changeQuestionOption(option);
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
        alertManager.success("Опция №" + option.getSeqNo() + " сохранена успешно!");
        Link returnLink = pageRenderLinkSource.createPageRenderLinkWithContext(QuestionOptions.class, option.getQuestionId());
        return returnLink;
    }

    void onFailure() {
        // versionFlash = question.getVersion();
    }

}
