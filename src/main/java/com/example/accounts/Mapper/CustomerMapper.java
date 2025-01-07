package com.example.accounts.Mapper;

import com.example.accounts.Entity.Customer;
import com.example.accounts.dto.CustomerDTO;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }
    public static Customer mapToCustomer(CustomerDTO customerDTO , Customer customer){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
