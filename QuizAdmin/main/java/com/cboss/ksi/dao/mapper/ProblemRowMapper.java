package com.cboss.ksi.dao.mapper;

import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.dao.util.AbstractRowMapper;

import java.sql.*;

/**
 * RowMapper for {@link com.cboss.ksi.common.entity.Problem}
 *
 * Created by ishafigullin on 22.06.2017.
 */
public class ProblemRowMapper extends AbstractRowMapper<Problem> {

    public static final ProblemRowMapper INSTANCE = new ProblemRowMapper();

    @Override
    public Problem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Problem object = new Problem(getLong(rs, "id"));
        object.setBody(getString(rs, "body"));
        object.setSeqNo(getInteger(rs, "seqNo"));
        object.setFromDate(getDate(rs, "fd"));
        object.setToDate(getDate(rs, "td"));
        object.setLimitTime(getInteger(rs, "limit_time_sec"));
        object.setStatus(getProblemStatus(rs, "d_status"));
        object.setRound(getInteger(rs, "round"));
        return object;
    }
}
