package com.cboss.ksi.common.util;

import java.io.Serializable;

/**
 * Created by ishafigullin on 23.06.2017.
 */
public class SortCriterion  implements Serializable {

    private String propertyName;
    private SortDirection sortDirection;

    public SortCriterion(String propertyName, SortDirection sortDirection) {
        this.propertyName = propertyName;
        this.sortDirection = sortDirection;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

}
