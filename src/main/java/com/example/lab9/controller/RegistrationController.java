package com.example.lab9.controller;

import com.example.lab9.domain.User;
import com.example.lab9.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/login")
    public String log(@RequestParam(required = false) String error, Map<String, Object> model) {
        fillFields(model,"Login","/registration","Registration?","Sign In");
        if(error != null)model.put("worn_message","Wrong login or password!");
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        fillFields(model,"Registration","/login","Sign in", "Sign Up");
        return "login";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        System.out.println(user.getUsername());
        if (userFromDb != null) {
            model.put("worn_message", "User exists!");
            return registration(model);
        }

        user.setEnabled(true);
        userRepo.save(user);

        return "redirect:/login";
    }

    private void fillFields(Map<String, Object> model, String... args){
        try {
            model.put("title", args[0]);
            model.put("href", args[1]);
            model.put("href_name", args[2]);
            model.put("button", args[3]);
        }catch (IndexOutOfBoundsException ignored){}
    }
}
