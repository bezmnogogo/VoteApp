package com.savchuk.services;

import com.savchuk.dao.entitties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by home on 14.11.16.
 */
@Service
@Transactional
public class UserDetailService implements IUserDetailsService {
    private final IUserService userService;

    @Autowired
    public UserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void changeUsername(User user, String username) {
    }
}
