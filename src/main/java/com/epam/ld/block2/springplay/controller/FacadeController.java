package com.epam.ld.block2.springplay.controller;

import com.epam.ld.block2.springplay.exception.RecordNotFoundException;
import com.epam.ld.block2.springplay.model.EventEntity;
import com.epam.ld.block2.springplay.model.TicketEntity;
import com.epam.ld.block2.springplay.model.UserEntity;
import com.epam.ld.block2.springplay.service.EventService;
import com.epam.ld.block2.springplay.service.TicketService;
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

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    @Autowired
    public FacadeController(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @GetMapping("index")
    public String gotoMainPage() {
        log.info("# # # FacadeController is asked to go to index page");
        return "index";
    }

    @GetMapping("users")
    public String getAllUsers(Model model) {
        log.info("# # # FacadeController is asked to get All Users.");
        List<UserEntity> all = userService.getAll();
        model.addAttribute("all", all);
        log.info("# # # FacadeController received list of all users. And size of list is {}", all.size());
        return "users";
    }

    @GetMapping("user/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to getUserById({})", id);
        UserEntity byId = userService.getUserById(id);
        model.addAttribute("entity", byId);
        return "user";
    }

    @GetMapping("users-by-name/{username}")
    public String getUsersByName(@PathVariable("username") String username, Model model) {
        List<UserEntity> users = userService.getUsersByName(username, 100, 1);
        model.addAttribute("all", users);
        log.info("# # # FacadeController is asked to getUsersByName user {}", username);
        return "users";
    }

    @GetMapping("user-by-email/{email}")
    public String getUserByEmail(@PathVariable("email") String email, Model model) {
        UserEntity user = userService.getUserByEmail(email, 100, 1);
        model.addAttribute("entity", user);
        log.info("# # # FacadeController is asked to getUserByEmail {}", email);
        return "user";
    }

    @GetMapping("new-user")
    public String newUserForm(Model model) {
        log.info("# # # FacadeController is asked to make form To Create User");
        UserEntity user = new UserEntity();
        model.addAttribute("entity", user);
        return "user-create";
    }

    @PostMapping("user-created")
    public String createUser(@ModelAttribute("user") UserEntity user, Model model) {
        log.info("createUser {}", user.toString());
        UserEntity newOne = userService.createUser(user);
        model.addAttribute("entity", newOne);
        log.info("# # # FacadeController receives form to createUser for {}", user);
        return "user-created";
    }

    @RequestMapping("user-update/{id}")
    public String editUserById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        log.info("# # # FacadeController is asked to make form To Update User");
        UserEntity user = userService.getUserById(id);
        userService.deleteUser(id);
        model.addAttribute("entity", user);
        return "user-create";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to delete User with id {}", id);
        boolean isDeleted = userService.deleteUser(id);
        model.addAttribute("id", id);
        model.addAttribute("status", isDeleted);
        log.info("# # # FacadeController report: User with id:{} has been eliminated", id);
        return "user-info";
    }


    @GetMapping("events")
    public String getAllEvents(Model model) {
        log.info("# # # FacadeController is asked to get All Events.");
        List<EventEntity> all = eventService.getAll();
        model.addAttribute("all", all);
        log.info("# # # FacadeController received list of all events. And size of list is {}", all.size());
        return "events";
    }

    @GetMapping("event/{id}")
    public String getEventById(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to get Event By Id {}", id);
        EventEntity byId = eventService.getEventById(id);
        model.addAttribute("byId", byId);
        return "event";
    }

    @GetMapping("events/{title}")
    public String getEventsByTitle(@PathVariable("title") String title, Model model) {
        List<EventEntity> events = eventService.getEventsByTitle(title, 100, 1);
        model.addAttribute("all", events);
        log.info("# # # FacadeController is asked to getEventsByTitle {}", title);
        return "events";
    }

    @GetMapping("new-event")
    public String newEventForm(Model model) {
        log.info("# # # FacadeController is asked to make form To Create Event");
        EventEntity entity = new EventEntity();
        model.addAttribute("entity", entity);
        return "event-create";
    }

    @PostMapping("event-created")
    public String createEvent(@ModelAttribute("entity") EventEntity entity, Model model) {
        log.info("createEvent {}", entity.toString());
        EventEntity newOne = eventService.createEvent(entity);
        model.addAttribute("entity", newOne);
        log.info("# # # FacadeController receives form to createEvent for {}", newOne);
        return "event-created";
    }

    @RequestMapping("event-update/{id}")
    public String editEventById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        log.info("# # # FacadeController is asked to make form To Update Event");
        EventEntity entity = eventService.getEventById(id);
        eventService.deleteEvent(id);
        model.addAttribute("entity", entity);
        return "event-create";
    }

    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to delete Event with id {}", id);
        boolean isDeleted = eventService.deleteEvent(id);
        model.addAttribute("id", id);
        model.addAttribute("status", isDeleted);
        log.info("# # # FacadeController report: Event with id:{} has been eliminated", id);
        return "event-info";
    }

    @GetMapping("tickets")
    public String getAllTickets(Model model) {
        log.info("# # # FacadeController is asked to get All Tickets.");
        List<TicketEntity> all = ticketService.getAll();
        model.addAttribute("all", all);
        log.info("# # # FacadeController received list of all tickets. And size of list is {}", all.size());
        return "tickets";
    }

    @GetMapping("ticket/{id}")
    public String getTicketById(@PathVariable("id") long id, Model model) {
        log.info("# # # FacadeController is asked to get Ticket By Id {}", id);
        TicketEntity entity = ticketService.getTicketById(id);
        model.addAttribute("entity", entity);
        return "ticket";
    }

}
