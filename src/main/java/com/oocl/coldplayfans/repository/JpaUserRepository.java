package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Integer> {
    User findUserByUsernameAndPassword(String username, String password);
}
