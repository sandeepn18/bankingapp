package com.example.accounts.service;

import com.example.accounts.dto.CustomerDetailsDTO;

public interface ICustomerService {
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
