package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Test.Test;
import com.savchuk.dao.repository.Test.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by home on 16.05.17.
 */
@Service
public class TestService implements ITestService {

    private final ITestRepository testRepository;

    @Autowired
    public TestService(ITestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.saveAndFlush(test);
    }
}
