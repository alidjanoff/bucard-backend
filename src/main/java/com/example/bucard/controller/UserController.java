package com.example.bucard.controller;

import com.example.bucard.model.dto.LoginDto;
import com.example.bucard.model.dto.PlanDto;
import com.example.bucard.model.dto.RegisterDto;
import com.example.bucard.model.dto.UserDto;
import com.example.bucard.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody RegisterDto registerDto){
        userService.registerUser(registerDto);
    }

    @PatchMapping("/select-plan")
    public void selectPlan(@RequestBody PlanDto planDto){
        userService.selectPlan(planDto);
    }

    @PostMapping
    public UserDto login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @PostMapping("/send-otp")
    public void sendOtp(String mail){
        userService.sendOtp(mail);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(String mail, Integer otp) throws ExecutionException {
        return userService.verifyOtp(mail,otp);
    }
}
