package com.example.Library.service;

import com.example.Library.model.Admin;
import com.example.Library.repository.AdminRepository;
import com.example.Library.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String initAdmin(String username, String password) {
        if (adminRepository.count() > 0) {
            return "Un administrateur existe déjà";
        }

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(passwordEncoder.encode(password));
        adminRepository.save(admin);

        return "Administrateur créé avec succès";
    }

    public String login(String username, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);

        if (optionalAdmin.isEmpty()) {
            return null;
        }

        Admin admin = optionalAdmin.get();

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            return null;
        }

        return jwtUtil.generateToken(username);
    }
}