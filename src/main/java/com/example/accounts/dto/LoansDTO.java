package com.example.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Loans details")
public class LoansDTO {

 @NotEmpty(message = "mobile number can not be empty")
 @Pattern(regexp = "[0-9]{10}", message = "mobile number should be 10 digits")
 @Schema(description = "mobile number", example = "1234567890")
    private String mobileNumber;
    @NotEmpty(message = "Loan number can not be empty")
    @Pattern(regexp = "[0-9]{12}", message = "loan number should be 10 digits")
    @Schema(description = "loan number", example = "123456789054")
    private String loanNumber;

    @NotEmpty(message = "loan type can not be empty")
    private String loanType;
    @Positive(message = "total loan should be positive or zero")
    private int totalLoan;
    @PositiveOrZero(message = "paid loan should be positive or zero")
    private int paidLoan;
    @PositiveOrZero(message = "outstanding loan should be positive or zero")
    private int outstandingLoan;
}
