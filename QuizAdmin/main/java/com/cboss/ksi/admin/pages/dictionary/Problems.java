package com.cboss.ksi.admin.pages.dictionary;


import com.cboss.ksi.common.entity.*;
import com.cboss.ksi.common.util.SortCriterion;
import com.cboss.ksi.common.util.SortUtil;
import com.cboss.ksi.dao.DictionaryDao;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ValidationTracker;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.grid.SortConstraint;
import org.apache.tapestry5.internal.services.*;
import org.apache.tapestry5.ioc.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.services.*;

import java.util.List;

/**
 * List problems page.
 *
 * Created by ishafigullin on 21.06.2017.
 */
public class Problems {
    @Inject
    private DictionaryDao dictionaryDao;
    @Inject
    private AlertManager alertManager;

/*    @Inject
    private Messages messages;
    @Inject
    private BeanModelSource beanModelSource;

    @Property
    private final StringValueEncoder stringValueEncoder = new StringValueEncoder();*/
    @Property
    private List<Problem> problems;
    @Property
    private Problem problem;
    @Property
    @Persist
    private String filter;

/*    @Environmental
    private ValidationTracker validationTracker;*/

    // @OnEvent(value = EventConstants.ACTIVATE)
    void onActivate() {
        if (filter == null) {
            filter = "";
        }
    }

    // The code
    void setupRender() {
        // Get all problems - ask business service to find them (from the database)
        problems = dictionaryDao.getProblems(0, Integer.MAX_VALUE, filter , null);
    }

    // Handle event "delete"
    void onDelete(Long id/*, Integer version*/) {
        try {
            // dictionaryDaoDao.deleteProblems(id, version); // version control, if need
            dictionaryDao.deleteProblem(id);
            alertManager.success("Задание №" + id + " успешно удалено!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }

    void onReset() {
        filter = "";
    }
/*
 * Uncomment for more control:
    public GridDataSource getProblems() {
        return new GridDataSource() {
            private int startIndex;
            private List<Problem> instances;

            @Override
            public int getAvailableRows() {
                return dictionaryDaoDao.getProblemCount(filter);
            }

            @Override
            public void prepare(int startIndex, int endIndex, List<SortConstraint> sortConstraints) {
                this.startIndex = startIndex;
                List<SortCriterion> sortCriteria = SortUtil.toSortCriteria(sortConstraints);
                instances = dictionaryDaoDao.getProblems(startIndex, endIndex - startIndex + 1, filter , sortCriteria);
            }

            @Override
            public Object getRowValue(int index) {
                return instances.get(index - startIndex);
            }

            @Override
            public Class<?> getRowType() {
                return Problem.class;
            }
        };
    }

    public BeanModel<Problem> getProblemModel() {
        BeanModel<Problem> eventModel = beanModelSource.createDisplayModel(Problem.class, messages);
        for (String prop : eventModel.getPropertyNames()) {
            eventModel.get(prop).sortable(false);
        }
        return eventModel;
    }
*/
}
