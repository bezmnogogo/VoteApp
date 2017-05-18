package com.savchuk.services.User;

import com.savchuk.dao.entitties.Test.UserTestResult;
import com.savchuk.dao.repository.Test.IUserTestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
@Service
public class UserTestResultService implements IUserTestResultService{
    private final IUserTestResultRepository userTestResultRepository;

    @Autowired
    public UserTestResultService(IUserTestResultRepository userTestResultRepository) {
        this.userTestResultRepository = userTestResultRepository;
    }

    @Override
    public UserTestResult saveStartedTest(UserTestResult startedTest) {
        return userTestResultRepository.saveAndFlush(startedTest);
    }

    @Override
    public List<UserTestResult> findStartedTest(long userId, long testId) {
        return userTestResultRepository.findStartedTest(userId, testId);
    }
}
