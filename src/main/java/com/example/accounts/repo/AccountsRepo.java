package com.example.accounts.repo;

import com.example.accounts.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Long> {
}
