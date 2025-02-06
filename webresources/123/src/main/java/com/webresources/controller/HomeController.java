package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends Attributes {

    @GetMapping
    public String homeRedirect() {
        return "redirect:/about";
    }

    @GetMapping("/index")
    public String home() {
        return "redirect:/about";
    }

    @GetMapping("/resources")
    public String resources(Model model) {
        AddAttributesResources(model);
        return "resources";
    }

    @GetMapping("/filter")
    public String filter(Model model, @RequestParam String name) {
        AddAttributesFilter(model, name);
        return "resources";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam ResourceType type, @RequestParam ResourceStatus status) {
        AddAttributesResourceSearch(model, type, status);
        return "resources";
    }
}