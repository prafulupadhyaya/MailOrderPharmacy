package com.cognizant.authenticationservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidateResponse {
    private String username;
}
