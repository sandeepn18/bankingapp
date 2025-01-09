package com.example.accounts.controller;

import com.example.accounts.Constants.AccountConstants;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.ResponseDTO;
import com.example.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    @Autowired
    private IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
accountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));

    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> getAccount(@RequestParam @Pattern(regexp = ("^[0-9]{10}$"), message = "mobile number should be 10 digits") String mobileNumber ){
        CustomerDTO customerDTO = accountsService.getAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDTO);

    }
    @PutMapping("/update")
    public ResponseEntity<Boolean> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountsService.updateAcount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(isUpdated);
    }
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

}
