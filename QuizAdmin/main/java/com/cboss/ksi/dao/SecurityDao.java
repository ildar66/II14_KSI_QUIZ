package com.cboss.ksi.dao;

import com.cboss.ksi.common.entity.Event;
import com.cboss.ksi.common.entity.Problem;
import com.cboss.ksi.common.entity.User;
import com.cboss.ksi.common.searchfilter.UserSearchFilter;

import java.util.List;

/**
 * DAO for Security.
 *
 * Created by ishafigullin on 17.07.2017.
 */
public interface SecurityDao {
    List<User> findUsers(UserSearchFilter searchFilter);

    List<Problem> getNonBlockingUserProblems(Long userID, Integer round);

    List<Problem> getBlockingUserProblems(Long userID, Integer round);

    void blockUserProblem(Long userID, Long problemID);

    void unblockUserProblem(Long userID, Long problemID);

    List<Problem> getVipAccessProblems(Long userID);

    List<Problem> getNonVipAccessProblems(Long userID, Integer round);

    void includeToVipUserProblem(Long userID, Long problemID);

    void excludeFromVipUserProblem(Long userID, Long problemID);

    List<Event> getUserEvents(Long userID, String filter);

    void deleteEvent(Long eventID);
}
