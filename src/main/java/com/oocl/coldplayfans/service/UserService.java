package com.oocl.coldplayfans.service;

import com.oocl.coldplayfans.dao.User;
import com.oocl.coldplayfans.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userDbRepository;

    public User login(User user) {
        return userDbRepository.getUserByNameAndPassword(user);
    }

    public User register(User user) {
        return userDbRepository.register(user);
    }
}
