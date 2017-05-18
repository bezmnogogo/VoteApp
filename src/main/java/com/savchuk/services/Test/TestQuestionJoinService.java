package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Test.TestQuestionJoin;
import com.savchuk.dao.repository.Test.ITestQuestionJoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 16.05.17.
 */
@Service
public class TestQuestionJoinService implements ITestQuestionJoinService {

    private final ITestQuestionJoinRepository testQuestionJoinRepository;

    @Autowired
    public TestQuestionJoinService(ITestQuestionJoinRepository testQuestionJoinRepository) {
        this.testQuestionJoinRepository = testQuestionJoinRepository;
    }

    @Override
    public TestQuestionJoin saveTestQuestionJoin(TestQuestionJoin testQuestionJoin) {
        return testQuestionJoinRepository.saveAndFlush(testQuestionJoin);
    }

    @Override
    public List<Question> getTestQuestionsByTestId(long testId) {
        return testQuestionJoinRepository.getTestQuestions(testId);
    }
}
