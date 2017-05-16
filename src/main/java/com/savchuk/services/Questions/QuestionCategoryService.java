package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.Question.QuestionCategory;
import com.savchuk.dao.repository.Question.IQuestionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 14.05.17.
 */
@Service
@Transactional
public class QuestionCategoryService implements IQuestionCategoryService{

    private final IQuestionCategoryRepository questionCategoryRepository;

    @Autowired
    public QuestionCategoryService(IQuestionCategoryRepository questionCategoryRepository) {
        this.questionCategoryRepository = questionCategoryRepository;
    }

    @Override
    public List<QuestionCategory> getUserQuestionCategories(long userId) {
        return questionCategoryRepository.getUserCategories(userId);
    }

    @Override
    public QuestionCategory addCategory(QuestionCategory category) {
        return questionCategoryRepository.saveAndFlush(category);
    }

    @Override
    public QuestionCategory getCategoryById(long id) {
        return questionCategoryRepository.findOne(id);
    }
}
