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
 * Problem Access(VIP) page.
 *
 * Created by ishafigullin on 24.07.2017.
 */
public class ProblemAccess {
    // The activation context
    private Long userID;

    // Generally useful bits and pieces
    @Inject
    private SecurityDao securityDao;
    @Inject
    private AlertManager alertManager;

    // Other pages
    @InjectPage
    private Users userSearch;

    // Screen fields
    @Property
    private List<Problem> vipAccessProblems;
    @Property
    private List<Problem> nonVipAccessProblems;
    @Property
    private Problem problem;
    @Property
    @Persist
    private Integer round;
    @InjectComponent
    private Zone userTableZone;

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long userID) {
        this.userID = userID;
        if (round == null) {
            round = 0;
        }
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return userID;
    }

    // The code
    void setupRender() {
        // Get all problems - ask business service to find them (from the database)
        vipAccessProblems = securityDao.getVipAccessProblems(userID);
        nonVipAccessProblems = securityDao.getNonVipAccessProblems(userID, round);
    }

    // Handle event "IncludeToVIP"
    void onIncludeToVIP(Long problemID/*, Integer version*/) {
        try {
            securityDao.includeToVipUserProblem(userID, problemID);
            alertManager.success("Задание №" + problemID + " включено в VIP!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }
    // Handle event "ExcludeVIP"
    void onExcludeVIP(Long problemID/*, Integer version*/) {
        try {
            securityDao.excludeFromVipUserProblem(userID, problemID);
            alertManager.success("Задание №" + problemID + " исключено из VIP!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }


    Object onValueChanged(Integer round)
    {
        vipAccessProblems = securityDao.getVipAccessProblems(userID);
        nonVipAccessProblems = securityDao.getNonVipAccessProblems(userID, round);
        return userTableZone.getBody();
    }

    Link onCancel() {
        return userSearch.createLinkWithLastSearch();
    }
}
