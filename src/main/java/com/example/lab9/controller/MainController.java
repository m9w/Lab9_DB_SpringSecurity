package com.example.lab9.controller;

import com.example.lab9.domain.*;
import com.example.lab9.utils.MakeableJSON;
import com.example.lab9.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.example.lab9.utils.Utils.*;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApplicationContext appContext;

    @GetMapping("/")
    public String general(Map<String, Object> model) {
        model.put("username", getUserName());
        return "main";
    }

    @PostMapping("/changePassword")
    public String changePassword(PasswordSet passwordSet, Map<String, Object> model) {
        if (!passwordSet.equalNewPass())putMessage(model,"New passwords not equal!","R");
        else {
            User user = userRepo.findByUsername(getUserName());
            if(!user.equalsPassword(passwordSet.old_password)) putMessage(model,"Old password invalid!","R");
            else {
                user.setPassword(passwordSet.new_password);
                userRepo.save(user);
                putMessage(model,"Success!","G");
            }
        }
        return general(model);
    }

    class PasswordSet{
        String old_password;
        String new_password;
        private String new2_password;

        public PasswordSet(String old_password, String new_password, String new2_password) {
            this.old_password = old_password;
            this.new_password = new_password;
            this.new2_password = new2_password;
        }
        boolean equalNewPass(){return new_password.equals(new2_password);}
    }

    @GetMapping("/q/{table}")
    public String queryTable(@PathVariable String table,
                             @RequestParam(required = false) String page,
                             @RequestParam(required = false) String pageSize,
                             Map<String, Object> model) {
        if(!ListAccessTables.contains(table)) throw new ResourceNotFoundException();
        PageRequest paging = PageRequest.of(StrToInt(page).orElse(0), StrToInt(pageSize,true).orElse(10));
        JpaRepository repo = appContext.getBean(table+"Repo", JpaRepository.class);
        model.put("JSON", MakeableJSON.toJSON(repo.findAll(paging)));
        return "JSON";
    }

    @PostMapping("/i/{table}")
    public String insertTableDept(@PathVariable String table, HttpServletRequest request, Map<String, Object> model){
        if(!ListAccessTables.contains(table)) throw new ResourceNotFoundException();
        try {
            appContext.getBean(table + "Repo", JpaRepository.class).save(
                    ListAccessTables.getObject(ListAccessTables.valueOf(table)).set(request.getParameterMap()));
            model.put("JSON","{\"status\":\"S\"}");
        }catch (DomainTable.setFieldException e) {
            model.put("JSON", "{\"status\":\"E\", \"message\":\""+e.getMessage()+"\"}");
        }
        return "JSON";
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {}

    private String getUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) return ((UserDetails)principal).getUsername();
        else return principal.toString();
    }

    void putMessage(Map<String, Object> model, String text, String tag){
        model.put("message",text);
        model.put("message_tag",tag);
    }
}

