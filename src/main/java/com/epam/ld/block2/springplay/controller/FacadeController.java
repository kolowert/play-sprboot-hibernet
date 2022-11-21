package com.epam.ld.block2.springplay.controller;

import com.epam.ld.block2.springplay.exception.RecordNotFoundException;
import com.epam.ld.block2.springplay.model.UserEntity;
import com.epam.ld.block2.springplay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        log.info("# # # FacadeController is asked to get All Users.");
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

    @GetMapping("users-by-name/{username}")
    public String getUsersByName(@PathVariable("username") String username, Model model) {
        List<UserEntity> users = service.getUsersByName(username, 100, 1);
        model.addAttribute("allUsers", users);
        log.info("# # # FacadeController is asked to getUsersByName user {}", username);
        return "users";
    }

    @GetMapping("user-by-email/{email}")
    public String getUserByEmail(@PathVariable("email") String email, Model model) {
        UserEntity user = service.getUserByEmail(email, 100, 1);
        model.addAttribute("user", user);
        log.info("# # # FacadeController is asked to getUserByEmail {}", email);
        return "user";
    }

    @GetMapping("new-user")
    public String newUserForm(Model model) {
        log.info("# # # FacadeController is asked to make form To Create User");
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("user-created")
    public String createUser(@ModelAttribute("user") UserEntity user, Model model) {
        log.info("createUser {}", user.toString());
        UserEntity newOne = service.createUser(user);
        model.addAttribute("newOne", newOne);
        log.info("# # # FacadeController receives form to createUser for {}", user);
        return "user-created";
    }

    @RequestMapping("user-update/{id}")
    public String editEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        log.info("# # # FacadeController is asked to make form To Update User");
        UserEntity user = service.getUserById(id);
        model.addAttribute("user", user);
        return "user-create";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to delete User with id {}", id);
        boolean isDeleted = service.deleteUser(id);
        model.addAttribute("id", id);
        model.addAttribute("status", isDeleted);
        log.info("# # # FacadeController report: User with id:{} has been eliminated", id);
        return "user-info.html";
    }
}
