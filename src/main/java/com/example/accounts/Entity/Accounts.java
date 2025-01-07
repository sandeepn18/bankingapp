package com.example.accounts.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
public class Accounts extends BaseEntity{
    private Long customerId;
    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;

}
