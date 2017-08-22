package com.cboss.ksi.dao.mapper;

import com.cboss.ksi.common.entity.Question;
import com.cboss.ksi.dao.util.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for {@link com.cboss.ksi.common.entity.Question}
 *
 * Created by ishafigullin on 22.06.2017.
 */
public class QuestionRowMapper extends AbstractRowMapper<Question> {

    public static final QuestionRowMapper INSTANCE = new QuestionRowMapper();

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question object = new Question(getLong(rs, "id"));
        object.setProblemId(getLong(rs, "r_problem_id"));
        object.setBody(getString(rs, "body"));
        object.setSeqNo(getInteger(rs, "seqNo"));
        object.setFromDate(getDate(rs, "fd"));
        object.setToDate(getDate(rs, "td"));
        object.setOptionsType(getOptionsType(rs, "d_options_type"));
        object.setUserOption(getBool(rs, "user_option"));
        object.setUserOptionLabel(getString(rs, "user_option_label"));
        return object;
    }


}
