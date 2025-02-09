package com.webresources.controller;

import com.webresources.controller.main.Attributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/analysis/ranking")
public class RankingController extends Attributes {

    @Autowired
    private ScoreRepository scoreRepo;


    @GetMapping
    public List<SiteRating> getRanking() {
      
        return scoreRepo.findAll();
    }

    public static class SiteRating {
        private String siteName;
        private float overallRating;

    

        public SiteRating(String siteName, float overallRating) {
            this.siteName = siteName;
            this.overallRating = overallRating;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public float getOverallRating() {
            return overallRating;
        }

        public void setOverallRating(float overallRating) {
            this.overallRating = overallRating;
        }
    }
}
