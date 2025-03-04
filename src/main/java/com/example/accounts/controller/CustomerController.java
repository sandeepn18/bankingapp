package com.example.accounts.controller;

import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.dto.CustomerDetailsDTO;
import com.example.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@Tag(name = "CRUD operations for accounts controller", description = "please try these APIs")
@RequestMapping(path= "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
    private final ICustomerService iCustomerService;

    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }


    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchCustomerDetails(@RequestParam @Pattern(regexp = "[0-9]{10}", message = "mobile number should be 10 digits") String mobileNumber){
       CustomerDetailsDTO customerDetailsDTO=  iCustomerService.fetchCustomerDetails(mobileNumber);

       return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDTO);




    }
}
