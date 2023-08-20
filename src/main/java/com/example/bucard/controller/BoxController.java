package com.example.bucard.controller;

import com.example.bucard.model.dto.BoxDto;
import com.example.bucard.model.dto.BoxRequestDto;
import com.example.bucard.service.BoxService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/box")
public class BoxController {
    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }
    @PostMapping
    public BoxDto createBox(@RequestBody BoxRequestDto boxDto){
         return boxService.createBox(boxDto);
    }

    @GetMapping("/check-profile")
    public Boolean checkProfileInBox(Long boxId,Long profileId){
        return boxService.checkProfileInBox(boxId,profileId);
    }

}
