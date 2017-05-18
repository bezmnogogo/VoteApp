package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.repository.Question.IQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 13.05.17.
 */
@Service
@Transactional
public class QuestionService implements IQuestionService{

    private final IQuestionRepository questionRepository;

    @Autowired
    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findUserQuestions(long userId) {
        return null;
    }

    @Override
    public Question findQuestionById(long questionId) {
        return questionRepository.findOne(questionId);
    }

    @Override
    public List<Question> findQuestionsByTypeId(long userId, long questionTypeId) {
        return null;
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    @Override
    public List<Question> findQuestionByUserId(long userId) {
        return questionRepository.getQuestionsByUserId(userId);
    }

    @Override
    public List<Question> findQuestionByCategoryId(long categoryId) {
        return questionRepository.getQuestionsByCategoryId(categoryId);
    }
}
