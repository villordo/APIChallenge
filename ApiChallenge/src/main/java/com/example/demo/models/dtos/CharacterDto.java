package com.example.demo.models.dtos;

import com.example.demo.models.Character;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterDto {
    private String image;
    private String name;

    public CharacterDto(Character character) {
        this.image = character.getImage();
        this.name = character.getName();
    }
}
