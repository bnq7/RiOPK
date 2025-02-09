package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.WebResource;
import com.webresources.model.Rating;
import com.webresources.model.enums.ResourceStatus;
import com.webresources.service.WebResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/resources")
public class WebResourceController extends Attributes {

    private final WebResourceService webResourceService;

    public WebResourceController(WebResourceService webResourceService) {
        this.webResourceService = webResourceService;
    }

    @GetMapping
    public String resources(Model model) {
        AddAttributes(model);
        model.addAttribute("resources", webResourceService.getAllResources());
        model.addAttribute("resourceStatuses", ResourceStatus.values());
        return "resources";
    }

    @GetMapping("/rate/{id}")
    public String rateResource(@PathVariable Long id) {
        webResourceService.rateResource(id, getUser().getId());
        return "redirect:/resources";
    }

    @PostMapping("/add")
    public String addResource(@RequestParam String name, @RequestParam String url, @RequestParam String description) {
        webResourceService.addWebResource(name, url, description);
        return "redirect:/resources";
    }

    @PostMapping("/edit/{id}")
    public String editResource(@RequestParam String name, @RequestParam String url, @RequestParam String description,
                               @RequestParam ResourceStatus status, @PathVariable Long id) {
        webResourceService.editWebResource(id, name, url, description, status);
        return "redirect:/resources";
    }

    @GetMapping("/analysis/{id}")
    @ResponseBody
    public WebResource analyzeResource(@PathVariable Long id) {
        return webResourceService.analyzeResource(id).orElse(null);
    }

    @GetMapping("/ranking")
    @ResponseBody
    public List<WebResource> getRankedResources() {
        return webResourceService.getRankedResources();
    }
}
