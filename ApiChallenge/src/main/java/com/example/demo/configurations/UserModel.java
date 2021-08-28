package com.example.demo.configurations;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel{
    private String username;
    private String password;
}
