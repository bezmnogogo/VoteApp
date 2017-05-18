package com.savchuk.services.User;

import com.savchuk.dao.entitties.Test.CreatedUserTestResult;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
public interface ICreatedUserTestResultService {

    CreatedUserTestResult saveStartedTest(CreatedUserTestResult createdUserStartedTest);

    List<CreatedUserTestResult> findStartedTest(long userId, long testId);

}
