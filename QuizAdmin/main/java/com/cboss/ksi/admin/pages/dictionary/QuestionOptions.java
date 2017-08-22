package com.cboss.ksi.admin.pages.dictionary;

import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 * List question options page.
 *
 * Created by ishafigullin on 30.06.2017.
 */
public class QuestionOptions {
    // The activation context
    private Long questionId;

    // Screen fields
    @Property
    private Question question;
    @Property
    private QuestionOption option;
    @Property
    private List<QuestionOption> options;

    // Generally useful bits and pieces
    @Inject
    private DictionaryDao dictionaryDao;
    @Inject
    private AlertManager alertManager;

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return questionId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long questionId) {
        this.questionId = questionId;
    }

    // setupRender() is called by tapestry at the start of rendering - it's good for things that are display only.
    void setupRender() throws Exception {
        // Convert the id into a Question.
        question = dictionaryDao.getQuestion(questionId);
        options = dictionaryDao.getQuestionOptions(0, Integer.MAX_VALUE, questionId, null);
        /*if (question == null) {
            throw new Exception("Database data has not been set up!");
        }*/
    }

    // Handle event "delete"
    void onDelete(Long optionId/*, Integer version*/) {
        try {
            // dictionaryDaoDao.deleteQuestions(id, version); // version control, if need
            dictionaryDao.deleteQuestionOption(optionId);
            alertManager.success("Опция №" + optionId + " успешно удалена!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }

}
