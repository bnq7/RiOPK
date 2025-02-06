package com.webresources.controller.main;

import com.webresources.model.Rating;
import com.webresources.model.User;
import com.webresources.model.Website;
import com.webresources.model.enums.*;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Attributes extends Main {

    protected void addAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void addAttributesEnums(Model model) {
        model.addAttribute("categories", WebsiteCategory.values());
        model.addAttribute("ratings", RatingScale.values());
    }

    protected void addAttributesUsers(Model model) {
        addAttributes(model);
        model.addAttribute("users", userRepo.findAllByOrderByRole());
        model.addAttribute("roles", UserRole.values());
    }

    protected void addAttributesWebsite(Model model, Long id) {
        addAttributes(model);
        model.addAttribute("website", websiteRepo.getReferenceById(id));
    }

    protected void addAttributesWebsites(Model model) {
        addAttributes(model);
        List<Website> websites = websiteRepo.findAll();
        model.addAttribute("websites", websites);
    }

    protected void addAttributesProfile(Model model) {
        addAttributes(model);
        addAttributesEnums(model);
        model.addAttribute("user", userRepo.getReferenceById(getUser().getId()));
    }

    protected void addAttributesRatings(Model model) {
        addAttributes(model);
        model.addAttribute("ratings", ratingRepo.findAll());
    }

    protected void addAttributesWebsiteSearch(Model model, WebsiteCategory category, RatingScale rating) {
        addAttributes(model);
        addAttributesEnums(model);
        model.addAttribute("websites", websiteRepo.findAllByCategoryAndRating(category, rating));
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedRating", rating);
    }

    protected void addAttributesStats(Model model) {
        addAttributes(model);
        HashMap<String, Integer> categories = new HashMap<>();
        HashMap<String, Integer> ratings = new HashMap<>();

        for (WebsiteCategory category : WebsiteCategory.values()) {
            categories.put(category.getName(), websiteRepo.countByCategory(category));
        }
        for (RatingScale rating : RatingScale.values()) {
            ratings.put(rating.getLabel(), ratingRepo.countByScale(rating));
        }

        model.addAttribute("categories", categories);
        model.addAttribute("ratings", ratings);

        List<Rating> topRatings = ratingRepo.findAllByOrderByScoreDesc();
        topRatings = topRatings.subList(0, Math.min(5, topRatings.size()));

        String[] topWebsiteNames = topRatings.stream().map(r -> r.getWebsite().getName()).toArray(String[]::new);
        int[] topWebsiteScores = topRatings.stream().mapToInt(Rating::getScore).toArray();

        model.addAttribute("topWebsiteNames", topWebsiteNames);
        model.addAttribute("topWebsiteScores", topWebsiteScores);
    }
}