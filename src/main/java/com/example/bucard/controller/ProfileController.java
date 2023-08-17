package com.example.bucard.controller;

import com.example.bucard.model.dto.AddToBoxDto;
import com.example.bucard.model.dto.ProfileDto;
import com.example.bucard.service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public void saveProfile(@RequestBody ProfileDto profileDto) {
        profileService.saveProfile(profileDto);
    }

    @PostMapping("/add-to-box")
    public void addToBox(@RequestBody AddToBoxDto addToBoxDto) {
        profileService.addToBox(addToBoxDto);
    }
}
