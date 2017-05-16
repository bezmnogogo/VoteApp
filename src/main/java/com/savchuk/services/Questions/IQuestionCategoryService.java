package com.savchuk.services.Questions;

import com.savchuk.dao.entitties.QuestionCategory;

import java.util.List;
import java.util.Set;

/**
 * Created by home on 14.05.17.
 */
public interface IQuestionCategoryService {
    List<QuestionCategory> getUserQuestionCategories(long userId);

    QuestionCategory addCategory(QuestionCategory category);

    QuestionCategory getCategoryById(long id);
}