package com.example.accounts.controller;

import com.example.accounts.Constants.AccountConstants;
import com.example.accounts.dto.AccountsContactInfoDto;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD operations for accounts controller", description = "please try these APIs")
public class AccountsController {

    @Autowired
    private IAccountsService accountsService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @PostMapping("/create")
    @Operation(summary = "Create a new account with the given customer data", description = "please try these APIs")
   @ApiResponse(description = "Account created successfully", responseCode = "201")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
accountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));

    }

    @Operation(summary = "Get an account with the given mobile number", description = "please try these APIs")
@ApiResponse(description = "Account fetched successfully", responseCode = "200")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> getAccount(@RequestParam @Pattern(regexp = ("^[0-9]{10}$"), message = "mobile number should be 10 digits") String mobileNumber ){
        CustomerDTO customerDTO = accountsService.getAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);

    }

    @Operation(summary = "Update an account with the given customer data", description = "please try these APIs")
    @PutMapping("/update")
    @ApiResponse(description = "Account updated successfully", responseCode = "200")
    public ResponseEntity<Boolean> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountsService.updateAcount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(isUpdated);
    }

    @Operation(summary = "Delete an account with the given mobile number", description = "please try these APIs")
    @ApiResponse(description = "Account deleted successfully", responseCode = "200")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam @Pattern(regexp = ("^[0-9]{10}$"), message = "mobile number should be 10 digits") String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }


    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }



}
