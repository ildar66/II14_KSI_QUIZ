package com.cboss.ksi.dao.mapper;

import com.cboss.ksi.common.entity.User;
import com.cboss.ksi.dao.util.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for {@link com.cboss.ksi.common.entity.User}
 *
 * Created by ishafigullin on 18.07.2017.
 */
public class UserRowMapper extends AbstractRowMapper<User> {
    public static final UserRowMapper INSTANCE = new UserRowMapper();

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User object = new User(getLong(rs, "id"));
        object.setLoginId(getString(rs, "loginId"));
        object.setFirstName(getString(rs, "firstName"));
        object.setLastName(getString(rs, "lastName"));
        object.setEmail(getString(rs, "email"));
        object.setExpiryDate(getDate(rs, "expiryDate"));
        return object;
    }
}
