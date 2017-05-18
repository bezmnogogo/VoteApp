package com.savchuk.controller;

import com.savchuk.dao.entitties.*;
import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Question.QuestionCategory;
import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.entitties.Question.QuestionType;
import com.savchuk.model.*;
import com.savchuk.services.IUserService;
import com.savchuk.services.ParseService;
import com.savchuk.services.Questions.IQuestionCategoryService;
import com.savchuk.services.Questions.IQuestionOptionService;
import com.savchuk.services.Questions.IQuestionService;
import com.savchuk.services.Questions.IQuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by home on 13.05.17.
 */

@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    private final IUserService userService;
    private final IQuestionService questionService;
    private final IQuestionTypeService questionTypeService;
    private final IQuestionCategoryService questionCategoryService;
    private final IQuestionOptionService questionOptionService;

    @Autowired
    public QuestionController(IUserService userService, IQuestionService questionService, IQuestionTypeService questionTypeService, IQuestionCategoryService questionCategoryService, IQuestionOptionService questionOptionService) {
        this.userService = userService;
        this.questionService = questionService;
        this.questionTypeService = questionTypeService;
        this.questionCategoryService = questionCategoryService;
        this.questionOptionService = questionOptionService;
    }

    @RequestMapping(value = "/getUserCategories", method = RequestMethod.GET)
    public HttpResponse getUserCategories(HttpServletRequest request) {
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
            if (user == null) return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        } else {
            return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        }

        List<QuestionCategory> categories = questionCategoryService.getUserQuestionCategories(user.getId());
//        User user = userService.findUserById(1);
//        List<Question> questions = questionService.findQuestionByUserId(user.getId());
//        return questions;
//        List<QuestionCategory> categories = questionCategoryService.getUserQuestionCategories(1);
        if (categories != null) {
            HttpQuestionCategoryResponse response = new HttpQuestionCategoryResponse(categories);
            return response;
        }

        return null;
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public HttpResponse addCategory(HttpServletRequest request) {
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        }

        if (user != null) {
            CategoryRequest categoryRequest = ParseService.parseRequest(CategoryRequest.class, request);
            QuestionCategory category = questionCategoryService.addCategory(new QuestionCategory(categoryRequest.getCategoryName(), user));
            if (category != null)
                return new HttpResponse();
            else
                return new HttpErrorResponse(500, "не получилось добавить категорию. попробуйте снова или обратитесь в службу поддержки.");
        }
        return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
    }

    @RequestMapping(value = "/getTypes")
    public HttpResponse getTypes(HttpServletRequest request) {
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        }

        if (user == null) {
            return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        }

        List<QuestionType> questionTypes = questionTypeService.getAllTypes();
        if(questionTypes == null){
            return new HttpErrorResponse(500, "Ошибка. невозможно найти типы вопросов. обратитесь в службу поддержки.");
        }

        return new HttpQuestionTypeResponse(questionTypes);
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public HttpResponse addQuestion(HttpServletRequest request){
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return new HttpErrorResponse(403, "Вам необходимо войти в систему!");
        }
        if (user == null) return new HttpErrorResponse(403, "Вам необходимо войти в систему!");

        HttpAddQuestionRequest questionRequest = ParseService.parseRequest(HttpAddQuestionRequest.class, request);

        QuestionCategory category = questionCategoryService.getCategoryById(questionRequest.getSelectedCategoryId());
        QuestionType type = questionTypeService.getQuestionTypeById(questionRequest.getSelectedTypeId());

        /* ADD new Question */
        Question question = new Question();
        question.setUser(user);
        question.setQuestionCategory(category);
        question.setQuestionType(type);
        question.setQuestion(questionRequest.getQuestion());
        Question savedQuestion = questionService.saveQuestion(question);

        QuestionOption savedOption;
        /*Add options to this question*/
        for(QuestionOption option : questionRequest.getSelectedQuestionOptions()){
            option.setQuestion(savedQuestion);
            savedOption = questionOptionService.saveQuestionOption(option);
        }

        return new HttpAddQuestionResponse(savedQuestion);
    }

    @RequestMapping(value = "/getQuestionsByCategory/{categoryId}", method = RequestMethod.GET)
    public List<Question> getQuestionsByCategory(HttpServletRequest request, @PathVariable long categoryId){
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return null;
        }
        if (user == null) return null;

        List<Question> questions = questionService.findQuestionByCategoryId(categoryId);
        return questions;
    }
}
