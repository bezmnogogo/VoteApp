package com.savchuk.controller;

import com.savchuk.dao.entitties.User;
import com.savchuk.model.HttpAddTestRequest;
import com.savchuk.model.HttpErrorResponse;
import com.savchuk.model.HttpResponse;
import com.savchuk.services.IUserService;
import com.savchuk.services.ParseService;
import com.savchuk.services.Questions.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by home on 15.05.17.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final IUserService userService;
    private final IQuestionService questionService;

    @Autowired
    public TestController(IUserService userService, IQuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpResponse addTest(HttpServletRequest request){
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return null;
        }
        if (user == null) return new HttpErrorResponse(500, "не удалось добавить тест.");

        HttpAddTestRequest test = ParseService.parseRequest(HttpAddTestRequest.class, request);

        return new HttpResponse();
    }
}
