package com.cboss.ksi.admin.pages.dictionary;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.common.util.SortCriterion;
import com.cboss.ksi.common.util.SortUtil;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import java.util.List;

/**
 * List questions page.
 *
 * Created by ishafigullin on 26.06.2017.
 */
public class Questions {
    // The activation context
    private Long problemId;

    // Screen fields
    @Property
    private Problem problem;
    @Property
    private Question question;
    @Property
    private List<Question> questions;

    // Generally useful bits and pieces
    @Inject
    private DictionaryDao dictionaryDao;
    @Inject
    private AlertManager alertManager;
/*    @Inject
    private Messages messages;
    @Inject
    private BeanModelSource beanModelSource;*/

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return problemId;
    }

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long problemId) {
        this.problemId = problemId;
    }

    // setupRender() is called by tapestry at the start of rendering - it's good for things that are display only.
    void setupRender() throws Exception {
        // Convert the id into a Problem.
        problem = dictionaryDao.getProblem(problemId);
        questions = dictionaryDao.getQuestions(0, Integer.MAX_VALUE, problemId, null);
        /*if (problem == null) {
            throw new Exception("Database data has not been set up!");
        }*/
    }

    // Handle event "delete"
    void onDelete(Long id/*, Integer version*/) {
        try {
            // dictionaryDaoDao.deleteQuestions(id, version); // version control, if need
            dictionaryDao.deleteQuestion(id);
            alertManager.success("Вопрос №" + id + " успешно удален!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }
/*
 * Uncomment for more control.
    public GridDataSource getQuestions() {
        return new GridDataSource() {
            private int startIndex;
            private List<Question> instances;

            @Override
            public int getAvailableRows() {
                return dictionaryDaoDao.getQuestionsCount(problemId);
            }

            @Override
            public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
                this.startIndex = startIndex;
                List<SortCriterion> sortCriteria = SortUtil.toSortCriteria(sortConstraints);
                instances = dictionaryDaoDao.getQuestions(startIndex, endIndex - startIndex + 1, problemId, sortCriteria);
            }

            @Override
            public Object getRowValue(int index) {
                return instances.get(index - startIndex);
            }

            @Override
            public Class<?> getRowType() {
                return Question.class;
            }
        };
    }

    public BeanModel<Question> getQuestionModel() {
        BeanModel<Question> eventModel = beanModelSource.createDisplayModel(Question.class, messages);
        for (String prop : eventModel.getPropertyNames()) {
            eventModel.get(prop).sortable(false);
        }
        return eventModel;
    }*/
}
