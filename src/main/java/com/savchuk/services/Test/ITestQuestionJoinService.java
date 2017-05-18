package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Test.TestQuestionJoin;

import java.util.List;

/**
 * Created by home on 16.05.17.
 */
public interface ITestQuestionJoinService {
    TestQuestionJoin saveTestQuestionJoin(TestQuestionJoin testQuestionJoin);

    List<Question> getTestQuestionsByTestId(long testId);
}
