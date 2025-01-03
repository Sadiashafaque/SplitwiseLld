package com.practice.splitexpense.services;

import com.practice.splitexpense.models.User;
import com.practice.splitexpense.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public User createUser(User user) {
        System.out.println(user.getId() + " " + user.getCreatedAt() + " " + user.getUpdatedAt());
        userRepository.save(user);
        System.out.println(user.getId() + " " + user.getCreatedAt() + " " + user.getUpdatedAt());
        return user;
    }
    public List<User> getUsers(List<Long> userIds)
    {
        return userRepository.findAllById(userIds);
    }
    public User getUser(Long userId)
    {
        return userRepository.findById(userId).orElse(null);
    }
}
