package com.savchuk.services.User;

import com.savchuk.dao.entitties.Test.CreatedUserTestResult;
import com.savchuk.dao.repository.ICreatedUserRepository;
import com.savchuk.dao.repository.Test.ICreatedUserTestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 17.05.17.
 */
@Service
public class CreatedUserTestResultService implements ICreatedUserTestResultService{
    private final ICreatedUserTestResultRepository createdUserTestResultRepository;

    @Autowired
    public CreatedUserTestResultService(ICreatedUserTestResultRepository createdUserStartedTestRepository) {
        this.createdUserTestResultRepository = createdUserStartedTestRepository;
    }

    @Override
    public CreatedUserTestResult saveStartedTest(CreatedUserTestResult createdUserStartedTest) {
        return createdUserTestResultRepository.save(createdUserStartedTest);
    }

    @Override
    public List<CreatedUserTestResult> findStartedTest(long userId, long testId) {
        return createdUserTestResultRepository.findStartedTest(userId, testId);
    }
}
