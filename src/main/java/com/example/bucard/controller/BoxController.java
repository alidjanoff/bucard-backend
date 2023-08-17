package com.example.bucard.controller;

import com.example.bucard.model.dto.BoxRequestDto;
import com.example.bucard.service.BoxService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/box")
public class BoxController {
    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }
    @PostMapping
    public void createBox(@RequestBody BoxRequestDto boxDto){
        boxService.createBox(boxDto);
    }
}
