package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question.QuestionType;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
public interface IQuestionTypeService {
    QuestionType getQuestionTypeByName(String typeName);

    List<QuestionType> getAllTypes();

    QuestionType getQuestionTypeById(long id);
}
