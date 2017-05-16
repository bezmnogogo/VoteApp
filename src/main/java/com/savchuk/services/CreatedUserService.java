package com.savchuk.services;

import com.savchuk.dao.entitties.CreatedUser;
import com.savchuk.dao.repository.ICreatedUserRepository;
import com.savchuk.services.ICreatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by home on 17.05.17.
 */
@Service
public class CreatedUserService implements ICreatedUserService {
    private final ICreatedUserRepository createdUserRepository;

    @Autowired
    public CreatedUserService(ICreatedUserRepository createdUserRepository) {
        this.createdUserRepository = createdUserRepository;
    }

    @Override
    public CreatedUser saveCreatedUser(CreatedUser user) {
        return createdUserRepository.saveAndFlush(user);
    }

    @Override
    public CreatedUser findCreatedUserByEmail(String email) {
        return createdUserRepository.findUserByEmail(email);
    }
}
