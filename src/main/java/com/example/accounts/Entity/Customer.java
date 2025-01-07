package com.example.accounts.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import com.example.accounts.Entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long customerId;
    private String name;
    private String email;
    private String address;
    private String mobileNumber;


}
