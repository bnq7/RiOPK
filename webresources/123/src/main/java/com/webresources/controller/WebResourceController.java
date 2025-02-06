package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.WebResource;
import com.webresources.model.Rating;
import com.webresources.model.enums.ResourceStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resources")
public class WebResourceController extends Attributes {

    @GetMapping
    public String resources(Model model) {
        AddAttributes(model);
        model.addAttribute("resources", webResourceRepo.findAll());
        model.addAttribute("resourceStatuses", ResourceStatus.values());
        return "resources";
    }

    @GetMapping("/rate/{id}")
    public String rateResource(@PathVariable Long id) {
        if (ratingRepo.findByResource_IdAndUser_Id(id, getUser().getId()) == null) {
            ratingRepo.save(new Rating(webResourceRepo.getReferenceById(id), getUser()));
        }
        return "redirect:/resources";
    }

    @PostMapping("/add")
    public String addResource(@RequestParam String name, @RequestParam String url, @RequestParam String description) {
        webResourceRepo.save(new WebResource(name, url, description));
        return "redirect:/resources";
    }

    @PostMapping("/edit/{id}")
    public String editResource(@RequestParam String name, @RequestParam String url, @RequestParam String description,
                               @RequestParam ResourceStatus status, @PathVariable Long id) {
        WebResource resource = webResourceRepo.getReferenceById(id);
        resource.setName(name);
        resource.setUrl(url);
        resource.setDescription(description);
        resource.setStatus(status);
        webResourceRepo.save(resource);
        return "redirect:/resources";
    }
}