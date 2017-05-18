package com.savchuk.services.Test;

import com.savchuk.dao.entitties.TestAnswer;

import java.util.List;

/**
 * Created by home on 18.05.17.
 */
public interface ITestAnswerService {
    TestAnswer saveAnswer(TestAnswer testAnswer);

    boolean saveAll(List<TestAnswer> testAnswers);
}
