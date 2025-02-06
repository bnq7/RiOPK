package com.webresources.service;

import com.webresources.repo.UserRepo;  // Переименованный репозиторий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResourceUserService implements UserDetailsService {

    private final UserRepo userRepo;  // Переименованный репозиторий

    @Autowired
    public ResourceUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);  // Поиск пользователя по имени
    }
}