package com.example.self.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.self.services.DeveloperService;

@Controller
public class DeveloperController {
    
    private DeveloperService developerService;

    @Autowired
    public void setDeveloperService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @RequestMapping("/")
    public String redirToList(){
        return "redirect:/developer/list";
    }

    @RequestMapping({"/developer/list", "/developer"})
    public String listDevelopers(Model model){
        model.addAttribute("developers", developerService.listAll());
        return "developer/list";
    }

    @RequestMapping("/developer/show/{id}")
    public String getDeveloper(@PathVariable String id, Model model){
        model.addAttribute("developer", developerService.getById(Long.valueOf(id)));
        return "developer/show";
    }
}
