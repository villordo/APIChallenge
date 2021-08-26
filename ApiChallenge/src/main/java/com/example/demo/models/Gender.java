package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table ( name = "Gender" )
public class Gender {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)//Autoincrement
    private Integer gender_id;

    private String name;
    private Boolean image;


}
