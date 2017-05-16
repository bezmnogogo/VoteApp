package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.repository.Question.IQuestionOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by home on 14.05.17.
 */
@Service
@Transactional
public class QuestionOptionService implements IQuestionOptionService{

    private final IQuestionOptionRepository questionOptionRepository;

    @Autowired
    public QuestionOptionService(IQuestionOptionRepository questionOptionRepository) {
        this.questionOptionRepository = questionOptionRepository;
    }

    @Override
    public QuestionOption saveQuestionOption(QuestionOption option) {
        return questionOptionRepository.saveAndFlush(option);
    }
}
