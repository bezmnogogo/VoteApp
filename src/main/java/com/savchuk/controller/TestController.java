package com.savchuk.controller;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.entitties.Notification;
import com.savchuk.dao.entitties.Question.Question;
import com.savchuk.dao.entitties.Question.QuestionOption;
import com.savchuk.dao.entitties.Test.*;
import com.savchuk.dao.entitties.TestAnswer;
import com.savchuk.dao.entitties.User;
import com.savchuk.model.*;
import com.savchuk.services.ICreatedUserService;
import com.savchuk.services.INotificationService;
import com.savchuk.services.IUserService;
import com.savchuk.services.ParseService;
import com.savchuk.services.Questions.IQuestionOptionService;
import com.savchuk.services.Questions.IQuestionService;
import com.savchuk.services.Test.*;
import com.savchuk.services.User.ICreatedUserTestResultService;
import com.savchuk.services.User.IUserTestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    private final IUserTestResultService userTestResultService;
    private final ICreatedUserTestResultService createdUserTestResultService;
    private final ITestAnswerService testAnswerService;
    private final IQuestionOptionService questionOptionService;
    private final INotificationService notificationService;


    @Autowired
    public TestController(IUserService userService, IQuestionService questionService, ITestService testService, ITestQuestionJoinService testQuestionJoinService, ICreatedUserService createdUserService, ITestAccessCreatedUserJoinService testAccessCreatedUserJoinService, ITestAccessUserJoinService testAccessUserJoinService, IUserTestResultService userTestResultService, ICreatedUserTestResultService createdUserTestResultService, ITestAnswerService testAnswerService, IQuestionOptionService questionOptionService, INotificationService notificationService) {
        this.userService = userService;
        this.questionService = questionService;
        this.testService = testService;
        this.testQuestionJoinService = testQuestionJoinService;
        this.createdUserService = createdUserService;
        this.testAccessCreatedUserJoinService = testAccessCreatedUserJoinService;
        this.testAccessUserJoinService = testAccessUserJoinService;
        this.userTestResultService = userTestResultService;
        this.createdUserTestResultService = createdUserTestResultService;
        this.testAnswerService = testAnswerService;
        this.questionOptionService = questionOptionService;
        this.notificationService = notificationService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpResponse addTest(HttpServletRequest request) {
        User user;
        TestQuestionJoin testQuestionJoin;
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
        test.setActive(true);
        test.setUseQuestionWeight(requestTest.getTestOptions().isUseQuestionWeight());
        test.setTitle(requestTest.getTestOptions().getTitle());
        test.setCreateTime(new java.sql.Date(new java.util.Date().getTime()));

        test = testService.saveTest(test);

        //TestQuestionJoin testQuestionJoin = new TestQuestionJoin();
        for (Question question : requestTest.getTestQuestions()) {
            int weight = 1;
            for (QuestionWeight w : requestTest.getQuestionWeights()){
                if(w.getQuestionId() == question.getId()){
                    weight = w.getWeight();
                }
            }
            testQuestionJoin = new TestQuestionJoin();
            testQuestionJoin.setQuestion(question);
            testQuestionJoin.setTest(test);
            testQuestionJoin.setQuestionWeight(weight);
            testQuestionJoin = testQuestionJoinService.saveTestQuestionJoin(testQuestionJoin);
        }

        CreatedUser createdUser;
        User addedAccessUser;
        TestAccessCreatedUserJoin createdAccess;
        TestAccessUserJoin userAccess;

        for (String email : requestTest.getTestPeople()) {
            userAccess = new TestAccessUserJoin();
            createdAccess = new TestAccessCreatedUserJoin();
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

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public HttpResponse getTests(HttpServletRequest request) {
        User user;
        if (request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long) request.getAttribute("AuthUserId"));
        } else {
            return null;
        }
        if (user == null) return new HttpErrorResponse(500, "Тебя не существует.");

        List<Test> tests = testService.findTestsByUserId(user.getId());

        return new HttpGetTestsResponse(tests);
    }

    @RequestMapping(value = "/getQuickInfo/{id}", method = RequestMethod.GET)
    public HttpResponse getQuickInfo(@PathVariable long id) {
        Test test = testService.getTestById(id);
        if (test == null || !test.isActive()) {
            return new HttpErrorResponse(404, "теста не существует.");
        }
        return new HttpTestQuickInfoResponse(test.getTitle());
    }

    @RequestMapping(value = "/getToPass/{email}/{testId}", method = RequestMethod.GET)
    public HttpResponse getToPass(@PathVariable long testId, @PathVariable String email) {
        CreatedUser createdUser;
        User user;
        TestAccessUserJoin testAccessUserJoin;
        TestAccessCreatedUserJoin testAccessCreatedUserJoin;
        UserTestResult userTestResult;
        CreatedUserTestResult createdUserTestResult;
        List<QuestionModel> questionModels = new ArrayList<>();

        Test test = testService.getTestById(testId);
        if (test != null && !test.isActive()) {
            return new HttpErrorResponse(403, "тест уже недоступен.");
        }
        List<Question> questions = testQuestionJoinService.getTestQuestionsByTestId(testId);
        if (questions == null) {
            return new HttpErrorResponse(500, "вопросов не существует. не в этот раз. Пока.");
        }
        for (Question question : questions) {
            QuestionModel model = new QuestionModel(question);
            questionModels.add(new QuestionModel(question));
        }
        user = userService.findUserByEmail(email);
        if (user != null) {
            testAccessUserJoin = testAccessUserJoinService.getTestAccessUserJoin(user.getId(), testId);
            if (testAccessUserJoin != null) {
                List<UserTestResult> startedTests = userTestResultService.findStartedTest(user.getId(), testId);
                if (startedTests == null || startedTests.size() == 0) {
                    userTestResult = new UserTestResult();
                    userTestResult.setTest(test);
                    userTestResult.setUser(user);
                    userTestResult.setStartTime(new java.sql.Date(new java.util.Date().getTime()));
                    userTestResult = userTestResultService.saveStartedTest(userTestResult);
                    if (userTestResult != null)
                        return new HttpPassTestResponse(questionModels);
                }
                return new HttpPassTestResponse(questionModels);
            }
        }
        createdUser = createdUserService.findCreatedUserByEmail(email);
        if (createdUser != null) {
            testAccessCreatedUserJoin = testAccessCreatedUserJoinService.getTestAccessCreatedUserJoin(createdUser.getId(), testId);
            if (testAccessCreatedUserJoin != null) {
                List<CreatedUserTestResult> startedTests = createdUserTestResultService.findStartedTest(createdUser.getId(), testId);
                if (startedTests == null || startedTests.size() == 0) {
                    createdUserTestResult = new CreatedUserTestResult();
                    createdUserTestResult.setCreatedUser(createdUser);
                    createdUserTestResult.setTest(test);
                    createdUserTestResult.setStartTime(new java.sql.Date(new java.util.Date().getTime()));
                    createdUserTestResult = createdUserTestResultService.saveStartedTest(createdUserTestResult);

                    if (createdUserTestResult != null)
                        return new HttpPassTestResponse(questionModels);
                }
                return new HttpPassTestResponse(questionModels);
            }
        }
        return new HttpErrorResponse(403, "У Вас нет доступа к этому тесту.");
    }

    @RequestMapping(value = "/pass/{email}/{testId}", method = RequestMethod.POST)
    public HttpResponse passTest(HttpServletRequest request, @PathVariable long testId, @PathVariable String email) {
        CreatedUser createdUser;
        User user;
        TestAccessUserJoin testAccessUserJoin;
        TestAccessCreatedUserJoin testAccessCreatedUserJoin;
        UserTestResult userTestResult;
        CreatedUserTestResult createdUserTestResult;
        List<TestAnswer> testAnswers = new ArrayList<>();

        createdUser = createdUserService.findCreatedUserByEmail(email);
        user = userService.findUserByEmail(email);
        //чекаем юзера
        if (user == null && createdUser == null) return new HttpErrorResponse(404, "ты кто?");

        //парсим запрос
        HttpPassTestRequest passedTest = ParseService.parseRequest(HttpPassTestRequest.class, request);

        List<Question> questions = testQuestionJoinService.getTestQuestionsByTestId(testId);

        //получаем все опции
        List<Long> optionsIds = new ArrayList<>();
        for (PassTestQuestion question : passedTest.getQuestions()) {
            for (PassTestQuestionOption option : question.getOptions()) {
                if (question.getQuestionTypeId() == 1 || question.getQuestionTypeId() == 2) {
                    if (option.getAnswer() == "true") {
                        optionsIds.add(option.getId());
                    }
                }
                if (question.getQuestionTypeId() == 3)
                    optionsIds.add(option.getId());
            }
        }

        List<QuestionOption> passedOptions = questionOptionService.findAllByIds(optionsIds);

        for (PassTestQuestion question : passedTest.getQuestions()) { //TODO: fix row answer
            if(question.getQuestionTypeId() == 3){
                for(QuestionOption passedOption : passedOptions){
                    if(passedOption.getId() == question.getOptions().get(0).getId() && !passedOption.getValue().equals(question.getOptions().get(0).getAnswer())){
                        passedOption.setValue(question.getOptions().get(0).getAnswer());
                        passedOption.setCorrect(false);
                    }
                }
            }
        }

        //доступен ли тест
        Test test = testService.getTestById(testId);
        if (test != null && !test.isActive()) {
            return new HttpErrorResponse(403, "тест уже недоступен.");
        }

//        for(Question question : questions){
//            for(PassTestQuestion passQuestion : passedTest.getQuestions()){
//                if(question.getId() == passQuestion.getId()){
//                    for(PassTestQuestionOption option : passQuestion.getOptions()){
//                        if(option.getAnswer() == "true") {
//                            if (questionAnswersMap.get(question) != null) {
//                                questionAnswersMap.get(question).add(option);
//                            } else {
//                                ArrayList<PassTestQuestionOption> passOptions = new ArrayList<>();
//                                passOptions.add(option);
//                                questionAnswersMap.put(question, passOptions);
//                            }
//                        }
//                    }
//                }
//            }
//        }

        Map<Question, List<QuestionOption>> questionAnswersMap = new HashMap<>();
        for (Question question : questions) {
            for (QuestionOption option : passedOptions) {
                if (question.getId() == option.getQuestion().getId()) {
                    if (questionAnswersMap.get(question) != null) {
                        questionAnswersMap.get(question).add(option);
                    } else {
                        ArrayList<QuestionOption> passOptions = new ArrayList<>();
                        passOptions.add(option);
                        questionAnswersMap.put(question, passOptions);
                    }
                }
            }
        }

        Integer valuation = null;
        if(!test.isUseQuestionWeight()) {
            valuation = testService.calculateValuetion(test, questionAnswersMap);
        }

        List<Options> savedOptions = new ArrayList<>();
        for(QuestionOption opt : passedOptions){
            savedOptions.add(new Options(opt));
        }
        passedOptions = null;

        //registered user
        if (user != null)
        {
            List<TestAnswer> answers = new ArrayList<>();
//            userTestResult = new UserTestResult();
            userTestResult = userTestResultService.findStartedTest(user.getId(), test.getId()).get(0);
            userTestResult.setEndTime(new java.sql.Date(new java.util.Date().getTime()));
            userTestResult.setValuation(valuation);
            userTestResult = userTestResultService.saveStartedTest(userTestResult);

            for(Options option : savedOptions){
                QuestionOption newOpt = questionOptionService.findById(option.getId());
                testAnswers.add(new TestAnswer(userTestResult,null, newOpt, option.getValue(),option.isCorrect()));
            }

            if(testAnswerService.saveAll(testAnswers)){
                Notification notification = notificationService.saveNotification(new Notification(test.getUser(), userTestResult,null,"Ваше тест прошел еще один человек на оценку " + valuation));
            }

            return new HttpResponse();
        }

        //unregistered user
        if (createdUser != null)
        {
            List<TestAnswer> answers = new ArrayList<>();
            createdUserTestResult = createdUserTestResultService.findStartedTest(createdUser.getId(), test.getId()).get(0);
            createdUserTestResult.setEndTime(new java.sql.Date(new java.util.Date().getTime()));
            createdUserTestResult.setValuation(valuation);
            createdUserTestResult = createdUserTestResultService.saveStartedTest(createdUserTestResult);

            for(QuestionOption option : passedOptions){
                QuestionOption newOpt = questionOptionService.findById(option.getId());
                testAnswers.add(new TestAnswer(null,createdUserTestResult,newOpt, option.getValue(),option.isCorrect()));
            }

            if(testAnswerService.saveAll(testAnswers)){
                Notification notification = notificationService.saveNotification(new Notification(test.getUser(), null,createdUserTestResult,"Ваше тест прошел еще один человек на оценку " + valuation));
            }

            return new HttpResponse();
        }
        return new HttpResponse();
    }

    class Options{
        long id;
        String value;
        boolean isCorrect;

        public Options(QuestionOption option) {
            this.id = option.getId();
            this.value = option.getValue();
            this.isCorrect = option.isCorrect();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isCorrect() {
            return isCorrect;
        }

        public void setCorrect(boolean correct) {
            isCorrect = correct;
        }
    }

}
