package com.oocl.coldplayfans.dto.mapper;

import com.oocl.coldplayfans.model.User;
import com.oocl.coldplayfans.dto.UserResponse;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
