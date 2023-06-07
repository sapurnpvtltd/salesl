package com.purnabhu.user.services;

import com.purnabhu.user.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    public User createUser(User user);
    public List<User> getAllUser();

    public boolean existsByUsername(String username);
    public boolean existsByuserEmailId(String userEmailId);
}
