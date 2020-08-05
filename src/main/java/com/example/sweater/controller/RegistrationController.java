package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService service;

    @GetMapping("/registration")
    String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("password2") String passwordConfirm,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isEmpty = StringUtils.isEmpty(passwordConfirm);
        if(isEmpty){
            model.addAttribute("password2Error","Password confirmation mustn't be empty");
        }
        boolean isPasswordsSame = user.getPassword() != null && user.getPassword().equals(passwordConfirm);
        if(!isPasswordsSame){
            model.addAttribute("password2Error","Password are different!");
        }
        if(bindingResult.hasErrors() || isEmpty||!isPasswordsSame){
            Map<String, String> errors = ConrollerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!service.addUser(user)) {
            model.addAttribute("usernameError","User already exist!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
      boolean isActivate = service.activateUser(code);

      if(isActivate){
          model.addAttribute("messageType","success");
          model.addAttribute("message","User was succesfully activated");
      }else{
          model.addAttribute("messageType","danger");
          model.addAttribute("message","Activation code is not found!");
      }
      return "login";
    }
}
