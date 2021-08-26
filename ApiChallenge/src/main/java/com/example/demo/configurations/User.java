package com.example.demo.configurations;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private String username;
    private String password;
}
