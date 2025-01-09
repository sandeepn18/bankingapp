package com.example.accounts.service.impl;

import com.example.accounts.Constants.AccountConstants;
import com.example.accounts.Entity.Accounts;
import com.example.accounts.Entity.Customer;
import com.example.accounts.Exceptions.CustomerAlreadyExistsException;
import com.example.accounts.Exceptions.CustomerNotFoundException;
import com.example.accounts.Mapper.AccountsMapper;
import com.example.accounts.Mapper.CustomerMapper;
import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.dto.CustomerDTO;
import com.example.accounts.repo.AccountsRepo;
import com.example.accounts.repo.CustomerRepo;
import com.example.accounts.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {
    @Autowired
    private AccountsRepo accountsRepo;
    @Autowired
    private CustomerRepo customerRepo;
    /**
     * Create a new account with the given customer data.
     *
     * @param customerDTO customer data to create a new account
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepo.findByMobileNumber(customer.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists" + customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("admin");
        customer.setAddress(AccountConstants.ADDRESS);
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setUpdatedBy("admin");
        Customer savedCustomer = customerRepo.save(customer);

        accountsRepo.save(createNewAccount(savedCustomer));


    }

    @Override
    public CustomerDTO getAccount(String mobileNumber) {
        Customer foundAccount = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new CustomerNotFoundException("Customer", "MobileNumber", mobileNumber)
        );

       Accounts accounts = accountsRepo.findByCustomerId(foundAccount.getCustomerId()).orElseThrow(
               () -> new CustomerNotFoundException("Account", "MobileNumber", foundAccount.getCustomerId().toString())
       );

       CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(foundAccount, new CustomerDTO());
       customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));


       return customerDTO;
    }

    @Override
    public boolean updateAcount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDto = customerDTO.getAccountsDTO();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new CustomerNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepo.findById(customerId).orElseThrow(
                    () -> new CustomerNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepo.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new CustomerNotFoundException("Customer", "MobileNumber", mobileNumber)
        );
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }
}
