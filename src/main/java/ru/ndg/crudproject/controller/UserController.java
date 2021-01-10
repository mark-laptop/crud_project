package ru.ndg.crudproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ndg.crudproject.model.User;
import ru.ndg.crudproject.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String showUpdateUserPage(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update_user";
    }

    @GetMapping(value = "/create")
    public String showCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        return "create_user";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/user";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}
