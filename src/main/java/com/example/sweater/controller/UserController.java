package com.example.sweater.controller;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",service.findAll());
        return "userList";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}")
    public String editUserForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "editUser";

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public String saveUser(@RequestParam String username,
                           @RequestParam Map<String,String> form,
                           @RequestParam("userId")User user
    ){
        service.saveUser(user,username,form);
        return "redirect:/user";

    }

    @GetMapping("profile")
    public String getProfile(@AuthenticationPrincipal User user,Model model){
    model.addAttribute("username",user.getUsername());
    model.addAttribute("email",user.getEmail());
    return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email){
        service.updateProfile(user,password,email);
        return "redirect:/user/profile";
    }
    @GetMapping("subscribe/{user}")
    public String subscribe(@PathVariable User user,
                            @AuthenticationPrincipal User currentUser
                            ){
        service.subscribe(currentUser,user);
        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(@PathVariable User user,
                            @AuthenticationPrincipal User currentUser
                            ){
        service.unsubscribe(currentUser,user);
        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("{type}/{user}/list")
    public String getUserList(@PathVariable String type,
                              @PathVariable User user,
                              Model model){
        model.addAttribute("userChannel",user);
        model.addAttribute("type",type);
        if("subscribers".equals(type)){
            model.addAttribute("users",user.getSubscribers());
        }
        else model.addAttribute("users",user.getSubscriptions());
        return "subscriptions";
    }
}