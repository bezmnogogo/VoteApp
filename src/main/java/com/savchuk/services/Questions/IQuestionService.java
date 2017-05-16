package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question;

import java.util.List;

/**
 * Created by home on 13.05.17.
 */
public interface IQuestionService {

    List<Question> findUserQuestions(long userId);

    Question findQuestionById(long questionId);

    List<Question> findQuestionsByTypeId(long userId, long questionTypeId);

    Question saveQuestion(Question question);

    List<Question> findQuestionByUserId(long userId);

    List<Question> findQuestionByCategoryId(long categoryId);
}
