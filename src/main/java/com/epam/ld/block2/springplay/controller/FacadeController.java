package com.epam.ld.block2.springplay.controller;

import com.epam.ld.block2.springplay.model.UserEntity;
import com.epam.ld.block2.springplay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class FacadeController {

    private final UserService service;

    @Autowired
    public FacadeController(UserService srv) {
        service = srv;
    }

    @GetMapping("index")
    public String gotoMainPage() {
        log.info("# # # FacadeController is asked to go to index page");
        return "index";
    }

    @GetMapping("users")
    public String getAllUsers(Model model) {
        log.info("# # # FacadeController is asked to getAll.");
        List<UserEntity> all = service.getAll();
        model.addAttribute("allUsers", all);
        log.info("# # # FacadeController received list of all users. And size of list is {}", all.size());
        return "users";
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to getUserById({})", id);
        UserEntity userById = service.getUserById(id);
        model.addAttribute("user", userById);
        return "user";
    }
}
