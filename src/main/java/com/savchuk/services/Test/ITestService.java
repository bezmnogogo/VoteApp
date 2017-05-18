package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.entitties.Test.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by home on 16.05.17.
 */
public interface ITestService {
    Test saveTest(Test test);

    List<Test> findTestsByUserId(long userId);

    Test getTestById(long id);

    int calculateValuetion(Test test, Map<Question, List<QuestionOption>> questions);
}
