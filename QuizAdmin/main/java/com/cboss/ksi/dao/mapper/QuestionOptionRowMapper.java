package com.cboss.ksi.dao.mapper;

import com.cboss.ksi.common.entity.QuestionOption;
import com.cboss.ksi.dao.util.AbstractRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper for {@link com.cboss.ksi.common.entity.QuestionOption}
 *
 * Created by ishafigullin on 03.07.2017.
 */
public class QuestionOptionRowMapper extends AbstractRowMapper<QuestionOption> {

    public static final QuestionOptionRowMapper INSTANCE = new QuestionOptionRowMapper();

    @Override
    public QuestionOption mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuestionOption object = new QuestionOption(getLong(rs, "id"));
        object.setQuestionId(getLong(rs, "r_question_id"));
        object.setBody(getString(rs, "body"));
        object.setBodyImg(getString(rs, "bodyImg"));
        object.setSeqNo(getInteger(rs, "seqNo"));
        object.setFromDate(getDate(rs, "fd"));
        object.setToDate(getDate(rs, "td"));
        return object;
    }
}
