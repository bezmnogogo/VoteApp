package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.QuestionType;
import com.savchuk.dao.repository.IQuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
@Service
@Transactional
public class QuestionTypeService implements IQuestionTypeService{

    private final IQuestionTypeRepository questionTypeRepository;

    @Autowired
    public QuestionTypeService(IQuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public QuestionType getQuestionTypeByName(String typeName) {
        return questionTypeRepository.getQuestionTypeByName(typeName);
    }

    @Override
    public List<QuestionType> getAllTypes() {
        return questionTypeRepository.findAll();
    }

    @Override
    public QuestionType getQuestionTypeById(long id) {
        return questionTypeRepository.findOne(id);
    }
}
