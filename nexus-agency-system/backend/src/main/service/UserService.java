package SpectraSystems.Nexus.service;

import com.nexus.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public User saveUser(User user) {
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
