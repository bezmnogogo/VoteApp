package com.savchuk.services;

import org.springframework.stereotype.Service;

/**
 * Created by home on 17.04.17.
 */

import com.savchuk.dao.entitties.Role;
import com.savchuk.dao.entitties.User;
import com.savchuk.dao.repository.IRoleRepository;
import com.savchuk.dao.repository.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) throws Exception {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;


    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void setUserRole(User user){
        long id = 1;
        Role role = roleRepository.findOne(id);
        HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    @Transactional
    public User saveCurrentUserWithDetailsUpdate(User user) {
        User savedUser = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser, savedUser.getPassword(), savedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return savedUser;
    }

    @Override
    @Transactional
    public void changeUserPassword(String email, String currentPassword, String newPassword) throws Exception {
//        User user = userRepository.
//        if (user == null)
//            throw new Exception("");
//        if (!user.getPassword().equals(currentPassword))
//            throw new Exception("");
//        user.setPassword(newPassword);
//        userRepository.saveAndFlush(user);
    }


    @Override
    public boolean checkIfMailExists(String mail) {
        return userRepository.checkIfMailExists(mail);
    }

    @Override
    @Transactional
    public User changeUserMail(long userId, String newMail) {
        User user = userRepository.findOne(userId);
        user.setEmail(newMail);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    @Override
    public User findUserById(long userId) {
        return userRepository.findOne(userId);
    }
}

