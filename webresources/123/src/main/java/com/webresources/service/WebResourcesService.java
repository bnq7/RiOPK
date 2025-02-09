package com.webresources.service;

import com.webresources.model.WebResource;
import com.webresources.model.Rating;
import com.webresources.model.enums.ResourceStatus;
import com.webresources.repository.WebResourceRepository;
import com.webresources.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebResourceService {

    private final WebResourceRepository webResourceRepo;
    private final RatingRepository ratingRepo;


    public WebResourceService(WebResourceRepository webResourceRepo, RatingRepository ratingRepo) {
        this.webResourceRepo = webResourceRepo;
        this.ratingRepo = ratingRepo;
    }


    public List<WebResource> getAllResources() {
        return webResourceRepo.findAll();
    }

    public WebResource getResourceById(Long id) {
        return webResourceRepo.findById(id).orElse(null);
    }


    public WebResource addWebResource(String name, String url, String description) {
        WebResource resource = new WebResource(name, url, description);
        return webResourceRepo.save(resource);
    }


    public WebResource editWebResource(Long id, String name, String url, String description, ResourceStatus status) {
        WebResource resource = webResourceRepo.findById(id).orElse(null);
        if (resource != null) {
            resource.setName(name);
            resource.setUrl(url);
            resource.setDescription(description);
            resource.setStatus(status);
            return webResourceRepo.save(resource);
        }
        return null;
    }


    public Rating rateResource(Long resourceId, Long userId) {
        WebResource resource = webResourceRepo.findById(resourceId).orElse(null);
        if (resource != null && ratingRepo.findByResource_IdAndUser_Id(resourceId, userId) == null) {
            Rating rating = new Rating(resource, userId);
            return ratingRepo.save(rating);
        }
        return null;
    }


    public ResourceStatus[] getAllStatuses() {
        return ResourceStatus.values();
    }
}
