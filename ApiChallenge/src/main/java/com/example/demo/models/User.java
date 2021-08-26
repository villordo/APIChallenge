package com.example.demo.models;

import com.example.demo.models.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue
    private Integer user_id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean active;
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

}
