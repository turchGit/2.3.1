package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private  UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String getUsers(Model model) {


        model.addAttribute("users",userService.getUsers());
        return "users";
    }
    @GetMapping("user/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "user";

    }
    @GetMapping("adduser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "userForm";
    }
    @PostMapping("adduser")
    public String newUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";

    }
    @PostMapping("user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @GetMapping("user/{id}/update")
    public String updateUser(@PathVariable("id") Long id,Model model){
        model.addAttribute("user",userService.getUserById(id));

        return "userUpdateForm";
    }

    @PostMapping("user/{id}/update")
    public String confirmUserUpdate(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
}
