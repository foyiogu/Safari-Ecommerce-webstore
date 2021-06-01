package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
