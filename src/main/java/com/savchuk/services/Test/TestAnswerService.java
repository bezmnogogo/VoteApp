package com.savchuk.services.Test;

import com.savchuk.dao.entitties.TestAnswer;
import com.savchuk.dao.repository.ITestAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 18.05.17.
 */
@Service
public class TestAnswerService  implements ITestAnswerService{
    private final ITestAnswerRepository testAnswerRepository;

    @Autowired
    public TestAnswerService(ITestAnswerRepository testAnswerRepository) {
        this.testAnswerRepository = testAnswerRepository;
    }

    @Override
    public TestAnswer saveAnswer(TestAnswer testAnswer) {
        return testAnswerRepository.saveAndFlush(testAnswer);
    }

    @Override
    public boolean saveAll(List<TestAnswer> testAnswers) {
        return testAnswerRepository.save(testAnswers).size() != 0;
    }
}
