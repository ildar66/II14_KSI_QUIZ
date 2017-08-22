package com.cboss.ksi.admin.base;

import com.cboss.ksi.common.entity.User;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Base page for web pages.
 *
 * Created by ishafigullin on 14.07.2017.
 */
public class SimpleBasePage {
    @Inject
    private Messages messages;

    protected Messages getMessages() {
        return messages;
    }

    public DateFormat getDateInputFormat() {
        // If you want to make this static or move it into Visit, first read
        // http://thread.gmane.org/gmane.comp.java.tapestry.user/20925
        // return new SimpleDateFormat(visit.getDateInputPattern());
        return new SimpleDateFormat(getDateInputPattern());
    }

    public String getDateInputPattern() {
        // return visitExists ? visit.getDateInputPattern() : User.defaultDateInputPattern;
        return User.defaultDateInputPattern;
    }
}
