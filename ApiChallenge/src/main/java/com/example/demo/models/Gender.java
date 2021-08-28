package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table ( name = "genders" )
public class Gender {

    @Id
    @Column(name = "id_gender")
    @GeneratedValue (strategy = GenerationType.IDENTITY)//Autoincrement
    private Integer id_gender;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private Boolean image;


}
