package com.cboss.ksi.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.cboss.ksi.common.entity.EventType;
import com.cboss.ksi.common.entity.ObjectType;
import com.cboss.ksi.common.entity.OptionsType;
import com.cboss.ksi.common.entity.Problem;
import org.springframework.jdbc.core.RowMapper;

// import ru.ildar66.bm.common.entity.Currency;

/**
 * abstract super class for spring RowMapper support.
 *
 * Created by ishafigullin on 22.06.2017.
 */
public abstract class AbstractRowMapper<T> implements RowMapper<T> {
    // mapping for primitive types:
    protected String getString(ResultSet rs, String columnLabel) throws SQLException {
        return rs.getString(columnLabel);
    }

    protected Integer getInteger(ResultSet rs, String columnLabel) throws SQLException {
        Integer value = rs.getInt(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Long getLong(ResultSet rs, String columnLabel) throws SQLException {
        Long value = rs.getLong(columnLabel);
        return rs.wasNull() ? null : value;
    }

    protected Date getDate(ResultSet rs, String columnLabel) throws SQLException {
        return rs.getTimestamp(columnLabel);
    }

    protected Boolean getBoolean(ResultSet rs, String columnLabel) throws SQLException {
        String value = rs.getString(columnLabel);
        return YesNoUtil.asBoolean(value);
    }

    protected boolean getBool(ResultSet rs, String columnLabel) throws SQLException {
        int value = rs.getInt(columnLabel);
        return value == 1;
    }

    // mapping for enumeration types:

   /* protected Currency getCurrency(ResultSet rs, String columnLabel) throws SQLException {
        String value = rs.getString(columnLabel);
        return value == null ? null : Currency.valueOf(value);
    }*/

    protected Problem.Status getProblemStatus(ResultSet rs, String columnLabel) throws SQLException {
        int value = rs.getInt(columnLabel);
        return value == 1 ? Problem.Status.ACCESSIBLE : Problem.Status.NOT_ACCESSIBLE;
    }

    protected OptionsType getOptionsType(ResultSet rs, String columnLabel) throws SQLException {
        int id = rs.getInt(columnLabel);
        return rs.wasNull() ? null : OptionsType.valueOf(id);
    }

    protected EventType getEventType(ResultSet rs, String columnLabel) throws SQLException {
        int id = rs.getInt(columnLabel);
        return rs.wasNull() ? null : EventType.valueOf(id);
    }

    protected ObjectType getObjectType(ResultSet rs, String columnLabel) throws SQLException {
        int id = rs.getInt(columnLabel);
        return rs.wasNull() ? null : ObjectType.valueOf(id);
    }
}