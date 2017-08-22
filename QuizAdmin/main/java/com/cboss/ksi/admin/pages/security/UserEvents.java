package com.cboss.ksi.admin.pages.security;

import com.cboss.ksi.common.entity.Event;
import com.cboss.ksi.dao.SecurityDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.List;

/**
 * Users events page.
 *
 * Created by ishafigullin on 31.07.2017.
 */
public class UserEvents {
    // The activation context
    @Property
    private Long userID;

    // Generally useful bits and pieces
    @Inject
    private SecurityDao securityDao;
    @Inject
    private AlertManager alertManager;

    // Screen fields
    @Property
    private List<Event> events;
    @Property
    private Event event;
    @Property
    @Persist
    private String filter;

    // Other pages
    @InjectPage
    private Users userSearch;

    // onActivate() is called by Tapestry to pass in the activation context from the URL.
    void onActivate(Long userID) {
        this.userID = userID;
        if (filter == null) {
            initFilter();
        }
    }

    // onPassivate() is called by Tapestry to get the activation context to put in the URL.
    Long onPassivate() {
        return userID;
    }

    // The code
    void setupRender() {
        events = securityDao.getUserEvents(userID, filter);
    }

    Link onCancel() {
        return userSearch.createLinkWithLastSearch();
    }

    // Handle event "delete"
    void onDelete(Long eventID /*, Integer version*/) {
        try {
            // securityDao.deleteUserEvent(userID, eventID, version); // version control, if need
            securityDao.deleteEvent(eventID);
            alertManager.success("Событие №" + eventID + " успешно удалено!");
        }
        catch (Exception e) {
            // Display the cause. In a real system we would try harder to get a user-friendly message.
            // errorMessage = ExceptionUtil.getRootCauseMessage(e); TODO alert for errorMessage
        }
    }

    // reset filter action
    void onReset() {
        initFilter();
    }

    private void initFilter() {
        filter = "";
    }
}
