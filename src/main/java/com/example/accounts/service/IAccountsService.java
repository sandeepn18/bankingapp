package com.example.accounts.service;

import com.example.accounts.Entity.Customer;
import com.example.accounts.dto.CustomerDTO;

public interface IAccountsService {
    /**
     * Create a new account with the given customer data.
     *
     * @param customerDTO customer data to create a new account
     */
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO getAccount(String mobileNumber );
    boolean updateAcount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
