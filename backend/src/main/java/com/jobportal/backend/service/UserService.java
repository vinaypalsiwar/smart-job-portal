package com.jobportal.backend.service;

import com.jobportal.backend.model.Role;
import com.jobportal.backend.model.User;
import com.jobportal.backend.repository.RoleRepository;
import com.jobportal.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(User user, String roleName) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    // ✅ SAFE METHOD (NO 500)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
