package com.cboss.ksi.admin.pages.security;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.dao.SecurityDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 * Users blocking page.
 *
 * Created by ishafigullin on 19.07.2017.
 */
public class UserBlocking {
    // The activation context
    private Long userID;

    // Generally useful bits and pieces
    @Inject
    private SecurityDao securityDao;
    @Inject
    private AlertManager alertManager;

    // Screen fields
    @Property
    private List<Problem> blockingProblems;
    @Property
    private List<Problem> nonBlockingProblems;
    @Property
    private Problem problem;
    @Property
    @Persist
    private Integer round;
    @InjectComponent
    private Zone userTableZone;

    // Other pages
    @InjectPage
    private Users userSearch;

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long userID) {
        this.userID = userID;
        if (round == null) {
            round = 1;
        }
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return userID;
    }

    // The code
    void setupRender() {
        // Get all problems - ask business service to find them (from the database)
        blockingProblems = securityDao.getBlockingUserProblems(userID, round);
        nonBlockingProblems = securityDao.getNonBlockingUserProblems(userID, round);
    }

    // Handle event "blocking"
    void onBlocking(Long problemID/*, Integer version*/) {
        try {
            securityDao.blockUserProblem(userID, problemID);
            alertManager.success("Задание №" + problemID + " успешно блокировано!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }

    // Handle event "Unblocking"
    void onUnblocking(Long problemID/*, Integer version*/) {
        try {
            securityDao.unblockUserProblem(userID, problemID);
            alertManager.success("Задание №" + problemID + " успешно разблокировано!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }

    Object onValueChanged(Integer round)
    {
        nonBlockingProblems = securityDao.getNonBlockingUserProblems(userID, round);
        blockingProblems = securityDao.getBlockingUserProblems(userID, round);
        return userTableZone.getBody();
    }

    Link onCancel() {
        return userSearch.createLinkWithLastSearch();
    }
}
