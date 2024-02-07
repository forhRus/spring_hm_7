package com.example.spring_hm_7.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
  @GetMapping("/")
  public String homePage(Model model){
    String title = "home";
    String text = "home page";
    model.addAttribute("title", title);
    model.addAttribute("text", text);
    return "home-page";
  }

  @GetMapping("/public-data")
  public String publicPage(Model model){
    String title = "public";
    String text = "public page";
    model.addAttribute("title", title);
    model.addAttribute("text", text);
    return "public-page";
  }

  @GetMapping("/private-data")
  public String privatePage(Model model){
    String title = "private";
    String text = "private page";
    model.addAttribute("title", title);
    model.addAttribute("text", text);
    return "private-page";
  }
}
