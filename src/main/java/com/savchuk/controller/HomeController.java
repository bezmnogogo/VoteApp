package com.savchuk.controller;

import com.savchuk.dao.entitties.Vote;
import com.savchuk.model.User;
import com.savchuk.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * Created by home on 07.03.17.
 */
@RestController
public class HomeController {


    private final VoteService voteService;

    @Autowired
    public HomeController(VoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/")
    String helloWorld(){
        voteService.saveVote(new Vote());
        return "Success";
    }

    @RequestMapping(value = "/vote")
    String vote(){
        return "Hello! this is Vote answer!";
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public User addUser(){
        return new User();
    }

    @RequestMapping(value="/addUser1", method = RequestMethod.POST, headers     =     "Content-Type=application/json")
    public User addUser1(@RequestBody User user){
        return user;
    }
}
