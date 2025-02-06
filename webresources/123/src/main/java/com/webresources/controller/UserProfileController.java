package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.Score;
import com.webresources.model.Users;
import com.webresources.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class UserProfileController extends Attributes {

    @GetMapping
    public String profile(Model model) {
        AddAttributesProfile(model);
        return "profile";
    }

    @GetMapping("/edit")
    public String profileEdit(Model model) {
        AddAttributesProfile(model);
        return "profile_edit";
    }

    @GetMapping("/ratings")
    public String profileRatings(Model model) {
        AddAttributesScore(model);
        return "profile_ratings";
    }

    @PostMapping("/ratings")
    public String updateRatings(@RequestParam int usability, @RequestParam int design, @RequestParam int content,
                                @RequestParam int security, @RequestParam int accessibility) {
        Score score = usersRepo.getReferenceById(getUser().getId()).getScore();
        score.setUsability(usability);
        score.setDesign(design);
        score.setContent(content);
        score.setSecurity(security);
        score.setAccessibility(accessibility);
        score.setSummary(usability + design + content + security + accessibility);
        scoreRepo.save(score);
        return "redirect:/profile";
    }

    @PostMapping("/edit")
    public String updateProfile(Model model, @RequestParam MultipartFile photo, @RequestParam String name,
                                @RequestParam String email, @RequestParam String website) {
        Users user = usersRepo.getReferenceById(getUser().getId());
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "users/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesProfile(model);
            model.addAttribute("message", "Ошибка загрузки фото!");
            return "profile_edit";
        }

        user.setName(name);
        user.setEmail(email);
        user.setWebsite(website);

        usersRepo.save(user);
        return "redirect:/profile";
    }
}