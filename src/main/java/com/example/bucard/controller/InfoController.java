package com.example.bucard.controller;

import com.example.bucard.model.dto.InfoDto;
import com.example.bucard.service.InfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/infos")
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public List<InfoDto> getInfos(){
        return infoService.getInfos();
    }

    @PostMapping
    public void addInfo(@RequestBody InfoDto infoDto){
        infoService.addInfo(infoDto);
    }
}
