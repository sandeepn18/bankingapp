package com.example.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class ResponseDTO {
    private String statusCode;
    private String message;

    public ResponseDTO(String message, String statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
