package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepo repos;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",repos.findAll());
        return "userList";
    }
    @GetMapping("{user}")
    public String editUserForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "editUser";

    }
    @PostMapping
    public String saveUser(@RequestParam String username,
                           @RequestParam Map<String,String> form,
                           @RequestParam("userId")User user
    ){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).
                map(Role::name).
                collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        repos.save(user);
        return "redirect:/user";

    }
}
