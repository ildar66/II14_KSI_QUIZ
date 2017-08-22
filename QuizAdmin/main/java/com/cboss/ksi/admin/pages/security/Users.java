package com.cboss.ksi.admin.pages.security;

import com.cboss.ksi.admin.base.SimpleBasePage;
import com.cboss.ksi.common.entity.User;
import com.cboss.ksi.common.searchfilter.UserSearchFilter;
import com.cboss.ksi.dao.SecurityDao;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * List users page.
 *
 * Created by ishafigullin on 14.07.2017.
 */
public class Users extends SimpleBasePage{
    static private final String ISO_DATE_PATTERN = "dd.MM.yyyy";

    // The options for userSearchFilter.active are null, true, and false but we
    // have to present them using
    // String[] instead of Boolean[] because the inbuilt type coercer coerces
    // null to false.
    // (see https://issues.apache.org/jira/browse/TAPESTRY-1928).
    // static private final String[] ACTIVE_OPTIONS = { "true", "false" };

    // Query string parameters

    @ActivationRequestParameter(value = "loginid")
    private String loginId;

    @ActivationRequestParameter(value = "firstname")
    private String firstName;

    @ActivationRequestParameter(value = "lastname")
    private String lastName;

    @ActivationRequestParameter(value = "email")
    private String email;

    // We could declare expiry to be a Date if we also contribute a ValueEncoder
    // for Date in AppModule.
    @ActivationRequestParameter(value = "expiry")
    private String expiry;

    @Property
    @ActivationRequestParameter(value = "show")
    private Boolean showResult;

    // Screen fields

    @Property
    private UserSearchFilter searchFilter = new UserSearchFilter();

    @Property
    private List<User> users;

    @Property
    private User user;

    // Generally useful bits and pieces

    @Persist
    private UserSearchFilter lastSearchFilter;

    @Persist
    private Boolean lastShowResult;

    @InjectComponent
    private Form form;

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    @Inject
    private SecurityDao securityDao;

    // The code

    void onActivate() {
        setSearchFilterFromRequest();
    }

    void setupRender() {
        users = null;

        if (showResult == Boolean.TRUE) {
            users = search(searchFilter);
            if (users == null) {
                users = new ArrayList<User>();
            }
        }

        lastSearchFilter = new UserSearchFilter(searchFilter);
        lastShowResult = showResult;
    }

    void onSuccess() {
        setRequest(searchFilter, true);
    }
/*
    void onSort() {
        setRequest(searchFilter, true);
    }
*/
    void onReset() {
        setRequest(new UserSearchFilter(), (Boolean) null);
        users = null;
    }
/*
    Object onNew() {
        return UserCreate.class;
    }

    void onDelete(Long id, Integer version) {

        if (form.isValid()) {

            // Delete the user from the database unless they've been modified
            // elsewhere

            try {
                User user = securityFinderService.findUser(id);
                if (!user.getVersion().equals(version)) {
                    form.recordError("Cannot delete user because has been updated or deleted since last displayed.  Please refresh and try again.");
                } else {
                    securityManagerService.deleteUser(user);
                }
            } catch (Exception e) {
                form.recordError(interpretBusinessServicesExceptionForDelete(e));
            }
        }
        setRequest(lastSearchFilter, lastShowResult);
    }
*/


    List<User> search(UserSearchFilter searchFilter) {
        // SearchOptions searchOptions = new SearchOptions();
        List<User> list = securityDao.findUsers(searchFilter /*, searchOptions*/);
        return list;
    }

    void setSearchFilterFromRequest() {

        // Set the search filter criteria from the request URL query string
        // fields.
        // We could have put the filter fields in the activation context, but
        // arguably it's more RESTful to use
        // query string for filter criteria. The URL is certainly a more
        // reliable bookmark this way.
        // Eg. See
        // http://blpsilva.wordpress.com/2008/04/05/query-strings-in-restful-web-services/

        searchFilter.setLoginId(loginId);
        searchFilter.setFirstName(firstName);
        searchFilter.setLastName(lastName);
        searchFilter.setEmailAddress(email);
        searchFilter.setExpiryDate(toDate(expiry));
    }

    void setRequest(UserSearchFilter search, Boolean showResult) {

        // Return a link with the non-null search filter criteria in it.
        // We could have used onPassivate to output the search fields as the
        // activation context, but arguably
        // it's more RESTful to use a query string for filter criteria. The URL
        // is certainly a more reliable
        // bookmark this way.
        // Eg. See
        // http://blpsilva.wordpress.com/2008/04/05/query-strings-in-restful-web-services/

        if (lastSearchFilter == null) {
            loginId = null;
            firstName = null;
            lastName = null;
            email = null;
            expiry = null;
        } else {
            loginId = search.getLoginId();
            firstName = search.getFirstName();
            lastName = search.getLastName();
            email = search.getEmailAddress();
            expiry = toString(search.getExpiryDate());
        }

        this.showResult = showResult;
    }

    private String toString(Date value) {
        DateFormat isoDateFormat = new SimpleDateFormat(ISO_DATE_PATTERN);
        return value == null ? null : isoDateFormat.format(value);
    }

    private Date toDate(String value) {
        try {
            DateFormat isoDateFormat = new SimpleDateFormat(ISO_DATE_PATTERN);
            return value == null ? null : isoDateFormat.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public Link createLinkWithLastSearch() {
        setRequest(lastSearchFilter, lastShowResult);

        Link link = pageRenderLinkSource.createPageRenderLink(this.getClass());
        return link;
    }
}
