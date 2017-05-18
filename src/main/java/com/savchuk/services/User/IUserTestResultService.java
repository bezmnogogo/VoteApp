package com.savchuk.services.User;


import com.savchuk.dao.entitties.Test.UserTestResult;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public interface IUserTestResultService {
    UserTestResult saveStartedTest(UserTestResult startedTest);

    List<UserTestResult> findStartedTest(long userId, long testId);
}
