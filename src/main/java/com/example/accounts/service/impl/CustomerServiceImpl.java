package com.example.accounts.service.impl;

import com.example.accounts.Entity.Accounts;
import com.example.accounts.Entity.Customer;
import com.example.accounts.Exceptions.CustomerNotFoundException;
import com.example.accounts.Mapper.AccountsMapper;
import com.example.accounts.Mapper.CustomerMapper;
import com.example.accounts.dto.AccountsDTO;
import com.example.accounts.dto.CardsDTO;
import com.example.accounts.dto.CustomerDetailsDTO;
import com.example.accounts.dto.LoansDTO;
import com.example.accounts.repo.AccountsRepo;
import com.example.accounts.repo.CustomerRepo;
import com.example.accounts.service.ICustomerService;
import com.example.accounts.service.client.CardsFeignClient;
import com.example.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        Customer foundAccount = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new CustomerNotFoundException("Customer", "MobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepo.findByCustomerId(foundAccount.getCustomerId()).orElseThrow(
                () -> new CustomerNotFoundException("Account", "MobileNumber", foundAccount.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(foundAccount, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoan(mobileNumber);
        customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());

        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCard(mobileNumber);
        customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());

        return customerDetailsDTO;
    }
}
