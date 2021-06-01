package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
