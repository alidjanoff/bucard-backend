package com.example.bucard.controller;

import com.example.bucard.model.dto.LoginDto;
import com.example.bucard.model.dto.PlanDto;
import com.example.bucard.model.dto.RegisterDto;
import com.example.bucard.model.dto.UserDto;
import com.example.bucard.service.UserService;
import com.google.zxing.WriterException;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid RegisterDto registerDto) throws ExecutionException, IOException, WriterException {
        userService.registerUser(registerDto);
    }

    @PatchMapping("/select-plan")
    public void selectPlan(@RequestBody PlanDto planDto) {
        userService.selectPlan(planDto);
    }

    @PostMapping
    public UserDto login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/send-otp")
    public void sendOtp(String phone, String fullName) throws ExecutionException {
        userService.sendOtp(phone,fullName);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(String phone, Integer otp) throws ExecutionException {
        return userService.verifyOtp(phone, otp);
    }

//    @GetMapping("/{id}")
//    public UserDto getUser(@PathVariable Long id) {
//        return userService.getUser(id);
//    }

    @DeleteMapping
    public void deleteUsers() {
        userService.deleteUsers();
    }

    @GetMapping
    public UserDto getUser(String token) {
        return userService.getUser(token);
    }


}
