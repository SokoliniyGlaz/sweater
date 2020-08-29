package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

import static com.example.sweater.controller.ConrollerUtils.getErrors;


@Controller
public class MainController {

    @Autowired
    private MessageService messageService;


    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String,Object>model){
        model.put("messages", messageService.findAll());
        return "main";
    }

    @PostMapping("/main")
        public String add(
                @AuthenticationPrincipal User user,
                @Valid Message message,
                BindingResult bindingResult,
                Model model,
                @RequestParam("file") MultipartFile file

    ) throws IOException {
          message.setAuthor(user);
          if(bindingResult.hasErrors()){
               Map<String,String> errorsMap = getErrors(bindingResult);
              model.mergeAttributes(errorsMap);
              model.addAttribute("message",message);
          }
          else {
              model.addAttribute("message",null);
              messageService.add(message,file);
          }
            model.addAttribute("messages", messageService.findAll());
            return "main";
        }




    @GetMapping("filter")
    public String filter(@RequestParam String filter,Map<String,Object>model){
       model.put("messages",messageService.findByTag(filter));
        return "main";
    }
    @GetMapping("user-messages/{user}")
    public String getUserMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam(required = false) Message message,
            Model model
    ){
        model.addAttribute("userChannel",user);
        model.addAttribute("messages",user.getMessages());
        model.addAttribute("subscriptionsCount",user.getSubscriptions().size());
        model.addAttribute("subscribersCount",user.getSubscribers().size());
        model.addAttribute("isSubscriber",user.getSubscribers().contains(currentUser));
        model.addAttribute("message",message);
        model.addAttribute("isCurrentUser",currentUser.equals(user));
        return "userMessages";
    }
    @PostMapping("user-messages/{user}")
    public String editMesssage(
            @AuthenticationPrincipal User currentuser,
            @PathVariable Long user,
            @RequestParam("id")Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file")MultipartFile file
    ) throws IOException {
        if(message.getAuthor().equals(currentuser)){
            if(!StringUtils.isEmpty(text)){
                message.setText(text);
            }
            if(!StringUtils.isEmpty(tag)){
                message.setTag(tag);
            }
            messageService.add(message,file);
        }
        return "redirect:/user-messages/" + user;
    }

}
