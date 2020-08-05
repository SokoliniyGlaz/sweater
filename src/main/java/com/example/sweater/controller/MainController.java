package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import com.example.sweater.domain.User;
import com.example.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static com.example.sweater.controller.ConrollerUtils.getErrors;


@Controller
public class MainController {
    @Autowired
    private MessageRepository repository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String,Object>model){
        Iterable<Message> messages =  repository.findAll();
        model.put("messages", messages);

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
              if (file != null && !file.getOriginalFilename().isEmpty()) {
                  File uploadDir = new File(uploadPath);
                  if (!uploadDir.exists()) {
                      uploadDir.mkdir();
                  }
                  String uuidFile = UUID.randomUUID().toString();
                  String resultFileName = uuidFile + "." + file.getOriginalFilename();
                  file.transferTo(new File(uploadPath + "/" + resultFileName));
                  message.setFilename(resultFileName);
              }
                  model.addAttribute("message",null);
                  repository.save(message);
          }

            Iterable<Message> messages = repository.findAll();
            model.addAttribute("messages", messages);
            return "main";
        }



    @GetMapping("filter")
    public String filter(@RequestParam String filter,Map<String,Object>model){
        Iterable<Message>messages;
        if(filter!=null && !filter.isEmpty()) {
            messages = repository.findByTag(filter);
        }else{
            messages = repository.findAll();
        }
       model.put("messages",messages);
        return "main";
    }
}
