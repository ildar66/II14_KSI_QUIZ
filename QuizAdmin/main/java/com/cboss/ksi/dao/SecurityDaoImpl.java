package com.cboss.ksi.dao;

import com.cboss.ksi.common.entity.Event;
import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.User;
import com.cboss.ksi.common.searchfilter.UserSearchFilter;
import com.cboss.ksi.dao.mapper.EventRowMapper;
import com.cboss.ksi.dao.mapper.ProblemRowMapper;
import com.cboss.ksi.dao.mapper.UserRowMapper;
import com.cboss.ksi.dao.util.AbstractDao;

import java.util.ArrayList;
import java.util.List;

/**
 *  DAO implementation for Security.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public class SecurityDaoImpl extends AbstractDao implements SecurityDao{
    @Override
    public List<User> findUsers(UserSearchFilter searchFilter) {
        ArrayList<Object> params = new ArrayList<Object>();
        String query = sql.get("FIND_USERS");
        query = filterFindUsersQuery(searchFilter, query, params);
        return getJdbcTemplate().query(query, params.toArray(), UserRowMapper.INSTANCE);
    }

    private String filterFindUsersQuery(UserSearchFilter searchFilter, String query, List<Object> params) {
        if (searchFilter.getEmailAddress() != null) {
            // query += " AND email = ? ";
            query += " AND lower(email) like lower('%' || ? || '%') ";
            params.add(searchFilter.getEmailAddress());
        }
        if (searchFilter.getExpiryDate() != null) {
            query += " AND td <= ? ";
            params.add(new java.sql.Date(searchFilter.getExpiryDate().getTime()));
        }
        if (searchFilter.getLoginId() != null) {
            query += " AND n = ? ";
            params.add(searchFilter.getLoginId());
        }
        return query;
    }

    @Override
    public List<Problem> getNonBlockingUserProblems(Long userID, Integer round) {
        Object[] args = { round, userID };
        return getJdbcTemplate().query(sql.get("GET_NON_BLOCKING_USER_PROBLEMS"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public List<Problem> getBlockingUserProblems(Long userID, Integer round) {
        Object[] args = { round, userID };
        return getJdbcTemplate().query(sql.get("GET_BLOCKING_USER_PROBLEMS"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public void blockUserProblem(Long userID, Long problemID) {
        Object[] args = { userID, problemID };
        getJdbcTemplate().update(sql.get("BLOCK_USER_PROBLEM"), args);
    }

    @Override
    public void unblockUserProblem(Long userID, Long problemID) {
        Object[] args = { userID, problemID };
        getJdbcTemplate().update(sql.get("UNBLOCK_USER_PROBLEM"), args);
    }

    @Override
    public List<Problem> getVipAccessProblems(Long userID) {
        Object[] args = { userID };
        return getJdbcTemplate().query(sql.get("GET_VIP_ACCESS_PROBLEMS"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public List<Problem> getNonVipAccessProblems(Long userID, Integer round) {
        Object[] args = { round, userID };
        return getJdbcTemplate().query(sql.get("GET_NON_VIP_ACCESS_PROBLEMS"), args, ProblemRowMapper.INSTANCE);
    }

    @Override
    public void includeToVipUserProblem(Long userID, Long problemID) {
        Object[] args = { userID, problemID };
        getJdbcTemplate().update(sql.get("INCLUDE_TO_VIP_USER_PROBLEM"), args);
    }

    @Override
    public void excludeFromVipUserProblem(Long userID, Long problemID) {
        Object[] args = { userID, problemID };
        getJdbcTemplate().update(sql.get("EXCLUDE_FROM_VIP_USER_PROBLEM"), args);
    }

    @Override
    public List<Event> getUserEvents(Long userID, String filter) {
        Object[] args = { userID, filter };
        return getJdbcTemplate().query(sql.get("GET_USER_EVENTS"), args, EventRowMapper.INSTANCE);
    }

    @Override
    public void deleteEvent(Long eventID) {
        Object[] args = { eventID };
        getJdbcTemplate().update(sql.get("DELETE_EVENT"), args);
    }
}
