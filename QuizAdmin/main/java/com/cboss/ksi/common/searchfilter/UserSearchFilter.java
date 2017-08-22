package com.cboss.ksi.common.searchfilter;

import java.util.Date;

/**
 * User Search Filter obj.
 *
 * Created by ishafigullin on 14.07.2017.
 */
public class UserSearchFilter {
    private String loginId = null;
    private String firstName = null;
    private String lastName = null;
    private String emailAddress = null;
    private Date expiryDate = null;

    public UserSearchFilter() {
    }

    public UserSearchFilter(String loginId, String firstName, String lastName, String emailAddress, Date expiryDate) {
        super();
        this.loginId = loginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.expiryDate = expiryDate;
    }

    public UserSearchFilter(UserSearchFilter copyFrom) {
        // No defensive copies are created here, since there are no mutable object fields (String is immutable)
        this(copyFrom.loginId, copyFrom.firstName, copyFrom.lastName, copyFrom.emailAddress, copyFrom.expiryDate);
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("UserSearchFilter: [");
        buf.append("loginId=" + loginId + ", ");
        buf.append("firstName=" + firstName + ", ");
        buf.append("lastName=" + lastName + ", ");
        buf.append("emailAddress=" + emailAddress + ", ");
        buf.append("expiryDate=" + expiryDate + ", ");
        buf.append("]");
        return buf.toString();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
