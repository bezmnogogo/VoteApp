package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Test.TestAccessCreatedUserJoin;
import com.savchuk.dao.repository.Test.ITestAccessCreatedUserJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by home on 17.05.17.
 */
@Service
public class TestAccessCreatedUserJoinService implements ITestAccessCreatedUserJoinService {
    private final ITestAccessCreatedUserJoinRepository testAccessCreatedUserJoinRepository;

    @Autowired
    public TestAccessCreatedUserJoinService(ITestAccessCreatedUserJoinRepository testAccessCreatedUserJoinRepository) {
        this.testAccessCreatedUserJoinRepository = testAccessCreatedUserJoinRepository;
    }

    @Override
    public TestAccessCreatedUserJoin saveUserAccess(TestAccessCreatedUserJoin testAccessCreatedUserJoin) {
        return testAccessCreatedUserJoinRepository.saveAndFlush(testAccessCreatedUserJoin);
    }
}
