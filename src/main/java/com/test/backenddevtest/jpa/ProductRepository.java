package com.test.backenddevtest.jpa;

import com.test.backenddevtest.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
