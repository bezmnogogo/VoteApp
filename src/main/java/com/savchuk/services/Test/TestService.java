package com.savchuk.services.Test;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.entitties.Test.Test;
import com.savchuk.dao.repository.Test.ITestQuestionJoinRepository;
import com.savchuk.dao.repository.Test.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by home on 16.05.17.
 */
@Service
public class TestService implements ITestService {

    private final ITestRepository testRepository;
    private final ITestQuestionJoinRepository testQuestionJoinRepository;

    @Autowired
    public TestService(ITestRepository testRepository, ITestQuestionJoinRepository testQuestionJoinRepository) {
        this.testRepository = testRepository;
        this.testQuestionJoinRepository = testQuestionJoinRepository;
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.saveAndFlush(test);
    }

    @Override
    public List<Test> findTestsByUserId(long userId) {
        return testRepository.findTestsByUserId(userId);
    }

    @Override
    public Test getTestById(long id) {
        return testRepository.findOne(id);
    }

    @Override
    public int calculateValuetion(Test test, Map<Question, List<QuestionOption>> questions) {
        int scale = test.getScale();
        int questionCount = questions.size();
        Map<Integer, List<Question>> correctAnswers = new HashMap<>();
        Map<Integer, List<Question>> weightQuestions = new HashMap<>();
        int tempWeight;

        for (Map.Entry<Question, List<QuestionOption>> entry : questions.entrySet()) {

            tempWeight = testQuestionJoinRepository.getTestQuestionWeight(test.getId(), entry.getKey().getId()); //TODO: расчитать стоимость вопросов
            if (weightQuestions.get(tempWeight) != null) {
                weightQuestions.get(tempWeight).add(entry.getKey());
            } else {
                List<Question> q = new ArrayList<>();
                q.add(entry.getKey());
                weightQuestions.put(tempWeight, q);
            }
            if (entry.getValue().size() == entry.getKey().getCorrectOptions().size() && entry.getValue().containsAll(entry.getKey().getCorrectOptions())) {
                if (correctAnswers.get(tempWeight) != null) {
                    correctAnswers.get(tempWeight).add(entry.getKey());
                } else {
                    List<Question> correctQuestions = new ArrayList<>();
                    correctQuestions.add(entry.getKey());
                    correctAnswers.put(tempWeight, correctQuestions);
                }
            }
        }

        return (scale / questions.size()) *correctAnswers.size() ;
    }
}
