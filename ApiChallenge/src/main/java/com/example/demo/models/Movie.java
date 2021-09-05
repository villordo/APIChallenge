package com.example.demo.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "movies")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {

    @Id
    @Column(name = "id_movie")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @JsonFormat(timezone = "GMT-03:00", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @Column(name = "creation_date")
    private Date date;

    @Column(name = "qualification")
    private Integer qualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gender", referencedColumnName = "id_gender")
    @JsonBackReference
    //@JsonIgnore
    private Gender gender;

    @JsonIgnore
    @ManyToMany ( mappedBy = "movies")
    private Set<Character> characters = new HashSet<Character>();

}
