package com.webresources.service;

import com.webresources.model.WebResource;
import com.webresources.model.Rating;
import com.webresources.model.enums.ResourceStatus;
import com.webresources.repository.WebResourceRepository;
import com.webresources.repository.RatingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<WebResource> getResourceById(Long id) {
        return webResourceRepo.findById(id);
    }

    public WebResource addWebResource(String name, String url, String description) {
        WebResource resource = new WebResource(name, url, description);
        return webResourceRepo.save(resource);
    }

    public Optional<WebResource> editWebResource(Long id, String name, String url, String description, ResourceStatus status) {
        return webResourceRepo.findById(id).map(resource -> {
            resource.setName(name);
            resource.setUrl(url);
            resource.setDescription(description);
            resource.setStatus(status);
            return webResourceRepo.save(resource);
        });
    }

    public Optional<Rating> rateResource(Long resourceId, Long userId) {
        return webResourceRepo.findById(resourceId).flatMap(resource -> {
            if (ratingRepo.findByResource_IdAndUser_Id(resourceId, userId) == null) {
                Rating rating = new Rating(resource, userId);
                return Optional.of(ratingRepo.save(rating));
            }
            return Optional.empty();
        });
    }

    public ResourceStatus[] getAllStatuses() {
        return ResourceStatus.values();
    }
}

