package com.infoera.sportshub_inventory.service;

import com.infoera.sportshub_inventory.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user, String companyId);

//    List<User> getAllUsersByCompany(String companyId);

//    User getUserById(String id, String companyId);

    User login(String email, String password, String companyId);

//    Optional<User> findByEmailAndCompanyId(String email, String companyId);
}