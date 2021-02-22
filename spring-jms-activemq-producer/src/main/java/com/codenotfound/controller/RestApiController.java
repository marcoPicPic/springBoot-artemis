package com.codenotfound.controller;

import com.codenotfound.domain.User;
import com.codenotfound.jms.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @Autowired
    Sender sender;


    @RequestMapping(value="/string")
    public String string(@RequestParam("msg")String msg){
        sender.send(msg);
        return "Done";
    }

    @RequestMapping(value = "/user")
    public String user() {
        sender.send(new User("John", "Doe"));
        return "Done";
    }

    @RequestMapping(value = "/list-user")
    public String listUser() {
        for (int i = 0; i < 1000; i++) {
            sender.send(new User("John" + i, "Doe"));
        }

        return "Done";
    }
}