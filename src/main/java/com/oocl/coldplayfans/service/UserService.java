package com.oocl.coldplayfans.service;

import com.oocl.coldplayfans.dao.User;
import com.oocl.coldplayfans.repository.UserDbRepository;
import com.oocl.coldplayfans.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDbRepository userDbRepository;

    public User login(User user) {
        User login = userDbRepository.getUserByName(user);
        if(login == null){
            throw new RuntimeException("用户名不存在");
        }
        String salt = login.getSalt();
        String storedHash = login.getPassword();
        String inputHash = PasswordUtil.encryptMD5WithSalt(user.getPassword(), salt);
        if (!inputHash.equals(storedHash)) {
            throw new RuntimeException("密码不存在");
        }
        return login;
    }

    public User register(User user) {
        if(userDbRepository.getUserByName(user) != null){
            throw new RuntimeException("用户名已存在");
        }
        user.setSalt(PasswordUtil.generateSalt());
        user.setPassword(PasswordUtil.encryptMD5WithSalt(user.getPassword(), user.getSalt()));
        return userDbRepository.register(user);
    }
}
