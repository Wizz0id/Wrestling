//package com.example.Wrestling.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.List;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Role implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//    @ManyToMany(mappedBy = "rolesList")
//    @ToString.Exclude
//    private List<User> userList;
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }
//    public Role(String name){
//        this.name = name;
//    }
//}
