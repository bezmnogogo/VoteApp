package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Test.TestAccessUserJoin;
import com.savchuk.dao.repository.Test.ITestAccessCreatedUserJoinRepository;
import com.savchuk.dao.repository.Test.ITestAccessUserJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by home on 17.05.17.
 */
@Service
public class TestAccessUserJoinService implements ITestAccessUserJoinService{
    private final ITestAccessUserJoinRepository testAccessUserJoinRepository;

    @Autowired
    public TestAccessUserJoinService(ITestAccessUserJoinRepository testAccessUserJoinRepository) {
        this.testAccessUserJoinRepository = testAccessUserJoinRepository;
    }

    @Override
    public TestAccessUserJoin addUserAccess(TestAccessUserJoin testAccessUserJoin) {
        return testAccessUserJoinRepository.saveAndFlush(testAccessUserJoin);
    }
}
