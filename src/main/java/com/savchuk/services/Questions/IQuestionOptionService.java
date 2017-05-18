package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question.QuestionOption;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
public interface IQuestionOptionService {

    QuestionOption saveQuestionOption(QuestionOption option);

    List<QuestionOption> findAllByIds(List<Long> optionsIds);

    QuestionOption findById(long id);
}
