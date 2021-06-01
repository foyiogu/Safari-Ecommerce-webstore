package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Long> {
}
