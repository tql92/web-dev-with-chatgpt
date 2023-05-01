package com.example.thymeleaf;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping({ "/", "/home", "/index.html" })
    public ModelAndView home() {
        return new ModelAndView("index", new HashMap<String, Object>() {
            {
                // https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way
                put("greeting", "hello world!");
            }
        });
    }

}
