package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ratings")
public class RatingsController extends Attributes {

    @GetMapping
    public String ratings(Model model) {
    
        AddAttributes(model);

   
        model.addAttribute("controllerInfo", "Этот маршрут принадлежит контроллеру RatingsController с роутом /ratings");

        model.addAttribute("ratings", scoreRepo.findAll());

    
        return "ratings";
    }
}
