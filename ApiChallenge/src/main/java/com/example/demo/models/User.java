package com.example.demo.models;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.dtos.PostUserDto;
import com.example.demo.models.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id_user;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "pass")
    private String password;

    @Column(name = "rol")
    @Enumerated(value = EnumType.STRING)
    private Rol rol;

    public static User fromPostDto(PostUserDto userDto) throws NotValidRolException {

        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .rol(Rol.getRol(userDto.getRol()))
                .build();
    }
}
