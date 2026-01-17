package com.infoera.sportshub_inventory.service.impl;

import com.infoera.sportshub_inventory.model.User;
import com.infoera.sportshub_inventory.repository.UserRepository;
import com.infoera.sportshub_inventory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user, String companyId) {
        if (userRepository.existsByEmailAndCompanyId(user.getEmail(), companyId)) {
            throw new RuntimeException("Email already exists in this company!");
        }

        user.setCompanyId(companyId);
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password, String companyId) {
        User user = userRepository.findByEmailAndCompanyId(email, companyId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid Password!");
        }

        return user;
    }
}