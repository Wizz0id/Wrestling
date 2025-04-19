package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String password;
    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
    }
}
