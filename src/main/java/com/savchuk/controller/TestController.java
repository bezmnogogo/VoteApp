package com.savchuk.controller;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Test.Test;
import com.savchuk.dao.entitties.Test.TestAccessCreatedUserJoin;
import com.savchuk.dao.entitties.Test.TestAccessUserJoin;
import com.savchuk.dao.entitties.Test.TestQuestionJoin;
import com.savchuk.dao.entitties.User;
import com.savchuk.model.HttpAddTestRequest;
import com.savchuk.model.HttpErrorResponse;
import com.savchuk.model.HttpResponse;
import com.savchuk.services.ICreatedUserService;
import com.savchuk.services.IUserService;
import com.savchuk.services.ParseService;
import com.savchuk.services.Questions.IQuestionService;
import com.savchuk.services.Test.ITestAccessCreatedUserJoinService;
import com.savchuk.services.Test.ITestAccessUserJoinService;
import com.savchuk.services.Test.ITestQuestionJoinService;
import com.savchuk.services.Test.ITestService;
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
    private final ITestService testService;
    private final ITestQuestionJoinService testQuestionJoinService;
    private final ICreatedUserService createdUserService;
    private final ITestAccessCreatedUserJoinService testAccessCreatedUserJoinService;
    private final ITestAccessUserJoinService testAccessUserJoinService;

    @Autowired
    public TestController(IUserService userService, IQuestionService questionService, ITestService testService, ITestQuestionJoinService testQuestionJoinService, ICreatedUserService createdUserService, ITestAccessCreatedUserJoinService testAccessCreatedUserJoinService, ITestAccessUserJoinService testAccessUserJoinService) {
        this.userService = userService;
        this.questionService = questionService;
        this.testService = testService;
        this.testQuestionJoinService = testQuestionJoinService;
        this.createdUserService = createdUserService;
        this.testAccessCreatedUserJoinService = testAccessCreatedUserJoinService;
        this.testAccessUserJoinService = testAccessUserJoinService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpResponse addTest(HttpServletRequest request) {
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return null;
        }
        if (user == null) return new HttpErrorResponse(500, "не удалось добавить тест.");

        HttpAddTestRequest requestTest = ParseService.parseRequest(HttpAddTestRequest.class, request);

        //create new Test, then save
        Test test = new Test();
        test.setUser(user);
        test.setScale(requestTest.getTestOptions().getScale());
        if (requestTest.getTestOptions().isTimeLimited())
            test.setTimeLimit(requestTest.getTestOptions().getTimeLimit());
        test.setUseQuestionWeight(requestTest.getTestOptions().isUseQuestionWeight());
        test.setTitle(requestTest.getTestOptions().getTitle());
        test.setCreateTime(new java.sql.Date(new java.util.Date().getTime()));

        test = testService.saveTest(test);

        TestQuestionJoin testQuestionJoin = new TestQuestionJoin();
        for (Question question : requestTest.getTestQuestions()) {
            testQuestionJoin.setQuestion(question);
            testQuestionJoin.setTest(test);
            testQuestionJoin = testQuestionJoinService.saveTestQuestionJoin(testQuestionJoin);
        }

        CreatedUser createdUser = new CreatedUser();
        User addedAccessUser = new User();
        TestAccessCreatedUserJoin createdAccess = new TestAccessCreatedUserJoin();
        TestAccessUserJoin userAccess = new TestAccessUserJoin();
        for (String email : requestTest.getTestPeople()) {
            createdUser = createdUserService.findCreatedUserByEmail(email);
            if (createdUser != null) {
                /*save new created user*/
//                createdUser.setCorporation(user.getCorporation());
//                createdUser.setEmail(email);
//                createdUser = createdUserService.saveCreatedUser(createdUser);

                createdAccess.setTest(test);
                createdAccess.setCreatedUser(createdUser);
                testAccessCreatedUserJoinService.saveUserAccess(createdAccess);
                continue;
            }
            if ((addedAccessUser = userService.findUserByEmail(email)) != null) {
                userAccess.setTest(test);
                userAccess.setUser(addedAccessUser);
                testAccessUserJoinService.addUserAccess(userAccess);
                continue;
            }
            createdUser = new CreatedUser();
            createdAccess = new TestAccessCreatedUserJoin();
            createdUser.setCorporation(user.getCorporation());
            createdUser.setEmail(email);
            createdUser = createdUserService.saveCreatedUser(createdUser);

            createdAccess.setTest(test);
            createdAccess.setCreatedUser(createdUser);
            createdAccess = testAccessCreatedUserJoinService.saveUserAccess(createdAccess);
        }


        return new HttpResponse();
    }
}
