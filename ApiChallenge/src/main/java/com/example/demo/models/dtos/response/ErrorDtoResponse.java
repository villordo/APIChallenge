package com.example.demo.models.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDtoResponse {

    private String exception;

    private String message;
}
