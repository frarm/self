package com.example.self.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.self.commands.DeveloperForm;
import com.example.self.domain.Developer;
import com.example.self.services.DeveloperService;

@Controller
public class DeveloperController {

    // https://www.youtube.com/watch?v=AbGxZmfWulU

    private DeveloperService developerService;

    @Autowired
    public void setDeveloperService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/developer/list";
    }

    @RequestMapping({ "/developer/list", "/developer" })
    public String listDevelopers(Model model) {
        model.addAttribute("developers", developerService.listAll());
        return "developer/list";
    }

    @RequestMapping("/developer/show/{id}")
    public String getDeveloper(@PathVariable String id, Model model) {
        model.addAttribute("developer", developerService.getById(Long.valueOf(id)));
        return "developer/show";
    }

    @RequestMapping("/developer/new")
    public String newDeveloper(Model model) {
        model.addAttribute("developerForm", new DeveloperForm());
        return "developer/developerForm";
    }

    @RequestMapping(value = "/developer", method = RequestMethod.POST)
    public String saveOrUpdateDeveloper(@Validated DeveloperForm developerForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "developer/developerForm";
        }

        Developer savedDeveloper = developerService.saveOrUpdateDeveloperForm(developerForm);

        return "redirect:/developer/list/" + savedDeveloper.getId();
    }
}
