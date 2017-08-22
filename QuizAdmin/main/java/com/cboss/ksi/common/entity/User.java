package com.cboss.ksi.common.entity;

import java.util.Date;

/**
 * User VO.
 *
 * Created by ishafigullin on 14.07.2017.
 */
public class User extends BaseEntity<Long>{
    static public final String defaultDateInputPattern = "dd.MM.yyyy";

    private String loginId;
    private String firstName;
    private String lastName;
    private String email;
    private Date expiryDate;

    public User(Long id) {
        super(id);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
