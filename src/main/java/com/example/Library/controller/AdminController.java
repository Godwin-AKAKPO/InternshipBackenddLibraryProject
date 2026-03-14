package com.example.Library.controller;

import com.example.Library.dto.LoginRequest;
import com.example.Library.service.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/init")
    public String initAdmin(@RequestBody LoginRequest request) {
        return adminService.initAdmin(request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        String token = adminService.login(request.getUsername(), request.getPassword());

        if (token == null) {
            return "Identifiants incorrects";
        }

        return token;
    }
}