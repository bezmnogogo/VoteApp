package com.savchuk.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savchuk.dao.entitties.User;
import com.savchuk.model.HttpErrorResponse;
import com.savchuk.model.HttpResponse;
import com.savchuk.model.HttpTokenResponse;
import com.savchuk.services.CryptoService;
import com.savchuk.services.IUserService;
import com.savchuk.services.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by home on 08.05.17.
 */
@RestController
@RequestMapping(value = "/identity")
public class IdentityController {

    private static final String TOKEN_SALT_STRING = "token may salt string";

    private final IUserService userService;

    @Autowired
    public IdentityController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    HttpResponse login(HttpServletRequest request) {
        User user;
        if(request.getAttribute("AuthUserId") != null) {
            user = userService.findUserById((long)request.getAttribute("AuthUserId"));
        }
        else {
            LoginRequest loginRequest = ParseService.parseRequest(LoginRequest.class, request);
            user = userService.findUserByEmailAndPassword(loginRequest.email, CryptoService.getSha512Token(loginRequest.password, null));
        }
        if (user != null) {
            return new HttpTokenResponse(CryptoService.encryptAes(String.valueOf(user.getId())));
        }

        return new HttpErrorResponse(403, "не залогинился. попробуй еще разок.");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    HttpResponse registration(HttpServletRequest request) {
        User user = new User();
        UserRequest userRequest = ParseService.parseRequest(UserRequest.class, request);
        //User user = ParseService.parseRequest(User.class, request);
        user.setCorporation(userRequest.getCorporation());
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(CryptoService.getSha512Token(userRequest.getPassword(), null));
        user.setEnabled(true);
        userService.setUserRole(user);
        if (userService.checkIfMailExists(user.getEmail())) {
            return new HttpErrorResponse(400, "email уже существует введите другой");
        }
        user = userService.saveUser(user);
        return new HttpTokenResponse(CryptoService.encryptAes(String.valueOf(user.getId())));
    }

    class LoginRequest {
        @JsonProperty
        String email;

        @JsonProperty
        String password;
    }

    class UserRequest{
        @JsonProperty
        private String email;

        @JsonProperty
        private String firstName;

        @JsonProperty
        private String lastName;

        @JsonProperty
        private String password;

        @JsonProperty
        private String corporation;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCorporation() {
            return corporation;
        }

        public void setCorporation(String corporation) {
            this.corporation = corporation;
        }
    }
}
