package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDbRepository implements UserRepository {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Override
    public User getUserByName(User user) {
        return jpaUserRepository.findUserByUsername(user.getUsername());
    }

    @Override
    public User register(User user) {
        return jpaUserRepository.save(user);
    }
}
