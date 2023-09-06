package com.example.bucard.controller;

import com.example.bucard.model.dto.AddToBoxDto;
import com.example.bucard.model.dto.ProfileDto;
import com.example.bucard.model.dto.ProfileResponse;
import com.example.bucard.model.dto.RemoveFromBoxDto;
import com.example.bucard.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<ProfileResponse> searchProfiles(String filter, Pageable pageable) {
        return profileService.searchProfiles(filter, pageable);
    }

    @PostMapping("/remove-from-box")
    public void removeFromBox(@RequestBody RemoveFromBoxDto removeFromBoxDto) {
        profileService.removeFromBox(removeFromBoxDto);
    }
}
