package com.cboss.ksi.common.util;

/**
 * Created by ishafigullin on 23.06.2017.
 */
public enum SortDirection {

    ASCENDING, DESCENDING, UNSORTED;

    public String toStringForJpql() {
        if (this == ASCENDING) {
            return "";
        } else if (this == DESCENDING) {
            return " desc";
        } else {
            return "";
        }
    }
}