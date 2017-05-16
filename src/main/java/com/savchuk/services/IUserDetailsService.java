package com.savchuk.services;

import com.savchuk.dao.entitties.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    void changeUsername(User user, String password);
}