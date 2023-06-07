package com.purnabhu.user.services;

import com.purnabhu.user.controllers.UserController;
import com.purnabhu.user.entities.ERole;
import com.purnabhu.user.entities.Roles;
import com.purnabhu.user.entities.User;
import com.purnabhu.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.purnabhu.user.entities.ERole.ROLE_ADMIN;
import static com.purnabhu.user.entities.ERole.ROLE_USER;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Override
    public User createUser(User user) {
        logger.info("Creating user...");
        Roles roles = new Roles();
        roles.setRoleId(user.getUserId());
        roles.setRoleName("ROLE_ADMIN");
        roles.setRoleDesc("Admin");
        HashSet roleSet = new HashSet();
        roleSet.add(roles);
        user.setRoles(roleSet);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Fetching all users...");
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByuserEmailId(String userEmailId) {
        return userRepository.existsByuserEmailId(userEmailId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
