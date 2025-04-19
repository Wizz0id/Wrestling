package com.example.Wrestling.mapper;

import com.example.Wrestling.dto.UserDTO;
import com.example.Wrestling.entity.User;

public class UserMapper {
    public static UserDTO ToDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword());
    }
    public static User ToEntity(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
