package com.infoera.sportshub_inventory.controller;

import com.infoera.sportshub_inventory.dto.request.LoginRequest;
import com.infoera.sportshub_inventory.dto.response.LoginResponse;
import com.infoera.sportshub_inventory.model.User;
import com.infoera.sportshub_inventory.service.UserService;
import com.infoera.sportshub_inventory.security.JwtService; // Aapko ye service banani hogi
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest,
            @RequestHeader("X-Company-ID") String companyId) {

        // 1. Database se user check karein
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword(), companyId);

        // 2. User ke liye Token generate karein jisme email aur companyId hidden ho
        String token = jwtService.generateToken(user.getEmail(), user.getCompanyId());

        // 3. Token aur user details return karein
        return ResponseEntity.ok(new LoginResponse(token, user.getEmail(), user.getCompanyId(), user.getName()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody User user,
            @RequestHeader("X-Company-ID") String companyId) {
        return ResponseEntity.ok(userService.registerUser(user, companyId));
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getUsers(
//            @RequestHeader("X-Company-ID") String companyId) {
//        // Note: Jab Security puri tarah lag jayegi, tab header ki zaroorat nahi hogi
//        // Hum Token se khud Company ID nikal lenge
//        return ResponseEntity.ok(userService.getAllUsersByCompany(companyId));
//    }
}