package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDbRepository implements UserRepository{

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Override
    public User getUserByNameAndPassword(User user) {
        return jpaUserRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public User register(User user) {
        return jpaUserRepository.save(user);
    }
}
