package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public User getUserByName(User user);

    User register(User user);
}
