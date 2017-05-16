package com.savchuk.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.omg.CORBA.Object;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by home on 07.03.17.
 */
@RestController
@RequestMapping(value = "/home")
public class HomeController {
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public Man vote(HttpServletRequest request){
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = null;
//        try {
//            br = request.getReader();
//            String str = null;
//            while ((str = br.readLine()) != null) {
//                sb.append(str);
//            }
//            JSONObject jObj = new JSONObject(sb.toString());
//            String name = jObj.getString("password");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return new Man("man");
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "home";
    }

    class Man{
        @JsonProperty("userName")
        String userName;

        @JsonProperty("password")
        String password;

        public Man(String str) {
            this.userName = str;
        }
    }

//    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
//    public User addUser(){
//        return new User();
//    }
//
//    @RequestMapping(value="/addUser1", method = RequestMethod.POST, headers     =     "Content-Type=application/json")
//    public User addUser1(@RequestBody User user){
//        return user;
//    }
}
