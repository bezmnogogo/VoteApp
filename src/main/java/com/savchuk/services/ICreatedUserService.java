package com.savchuk.services;

import com.savchuk.dao.entitties.CreatedUser;

/**
 * Created by home on 17.05.17.
 */
public interface ICreatedUserService {
    CreatedUser saveCreatedUser(CreatedUser user);

    CreatedUser findCreatedUserByEmail(String email);
}
