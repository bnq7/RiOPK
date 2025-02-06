package com.webresources.controller;

import com.hrdepartment.controller.main.Attributes;
import com.hrdepartment.model.AppAnswer;
import com.hrdepartment.model.Apps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/apps")
public class AppsCont extends Attributes {

    @GetMapping
    public String Apps(Model model) {
        AddAttributesApps(model);
        return "apps";
    }

    @GetMapping("/{id}/delete")
    public String AppDelete(@PathVariable Long id) {
        appsRepo.deleteById(id);
        return "redirect:/apps";
    }

    @PostMapping("/{id}/answer")
    public String AppAnswer(@RequestParam String text, @PathVariable Long id) {
        appAnswerRepo.save(new AppAnswer(text, getDateNow(), getUser(), appsRepo.getReferenceById(id)));
        return "redirect:/apps";
    }


    @PostMapping("/add")
    public String AppsAdd(@RequestParam String text) {
        appsRepo.save(new Apps(text, getDateNow(), getUser()));
        return "redirect:/apps";
    }
}
