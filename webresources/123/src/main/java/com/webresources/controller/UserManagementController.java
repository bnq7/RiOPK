package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import com.webresources.model.Users;
import com.webresources.model.enums.UserStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserManagementController extends Attributes {

    @GetMapping
    public String userList(Model model) {
        AddAttributesUsers(model);
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @RequestParam UserStatus status) {
        Users user = usersRepo.getReferenceById(id);
        user.setStatus(status);
        usersRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        if (user.equals(getUser())) {
            AddAttributesUsers(model);
            model.addAttribute("message", "Вы не можете удалить свой профиль!");
            return "users";
        }
        usersRepo.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/block/{id}")
    public String blockUser(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setStatus(UserStatus.BLOCKED);
        usersRepo.save(user);
        return "redirect:/users";
    }
}