package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name="Customer", description = "Customer details")
public class CustomerDTO {
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, message = "name should have at least 2 characters")
    @Email(message = "should be a valid email address")
    private String email;
    @NotEmpty(message = "should not be empty")
    @Pattern(regexp = ("^[0-9]{10}$"), message = "mobile number should be 10 digits")
    private String mobileNumber;
    private AccountsDTO accountsDTO;
}
