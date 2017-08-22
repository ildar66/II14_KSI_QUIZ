package com.cboss.ksi.dao.mapper;

import com.cboss.ksi.common.entity.Event;
import com.cboss.ksi.dao.util.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for {@link com.cboss.ksi.common.entity.Event}
 *
 * Created by ishafigullin on 01.08.2017.
 */
public class EventRowMapper extends AbstractRowMapper<Event> {
    public static final EventRowMapper INSTANCE = new EventRowMapper();

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event object = new Event(getLong(rs, "n"));
        object.setFromDate(getDate(rs, "fd"));
        object.setToDate(getDate(rs, "td"));
        object.setEventType(getEventType(rs, "d_event"));
        object.setObjectType(getObjectType(rs, "d_obj"));
        object.setObjId(getLong(rs, "obj_id"));
        object.setUserId(getLong(rs, "r_user_id"));
        object.setDsc(getString(rs, "dsc"));
        return object;
    }
}
