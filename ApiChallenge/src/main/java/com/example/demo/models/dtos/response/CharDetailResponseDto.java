package com.example.demo.models.dtos.response;

import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.Character;
import com.example.demo.models.Movie;
import com.example.demo.models.User;
import com.example.demo.models.dtos.MovieDto;
import com.example.demo.models.dtos.PostUserDto;
import com.example.demo.models.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharDetailResponseDto {
    private String name;
    private String image;
    private Integer age;
    private List<MovieDto> movies = new ArrayList<MovieDto>();

    public static CharDetailResponseDto buildResponse(Character character) {

        return CharDetailResponseDto.builder()
                .name(character.getName())
                .image(character.getImage())
                .age(character.getAge())
                .movies(character.getMovies().stream().map(MovieDto::new).collect(Collectors.toList()))
                .build();
    }

}
