package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.Resource;
import com.webresources.model.enums.ResourceStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resource")
public class ResourceCont extends Attributes {

    @GetMapping
    public String Resource(Model model) {
        AddAttributesResource(model);  // Здесь можно заменить на метод, подходящий для твоей логики
        return "resource";
    }

    @GetMapping("/under_evaluation/{id}")
    public String ResourceUnderEvaluation(@PathVariable Long id) {
        Resource resource = resourceRepo.getReferenceById(id);
        resource.setStatus(ResourceStatus.APPROVED);  // Пример смены статуса
        resourceRepo.save(resource);
        return "redirect:/resource";
    }

    @GetMapping("/rejected/{id}")
    public String ResourceRejected(@PathVariable Long id) {
        Resource resource = resourceRepo.getReferenceById(id);
        resource.setStatus(ResourceStatus.REJECTED);  // Пример смены статуса
        resourceRepo.save(resource);
        return "redirect:/resource";
    }
}