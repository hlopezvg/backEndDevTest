package com.test.backenddevtest.services;


import com.test.backenddevtest.domain.exceptions.ResourceNotFoundException;
import com.test.backenddevtest.jpa.ProductRepository;
import com.test.backenddevtest.persistence.entities.Product;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Either<ResourceNotFoundException, Product> findById(Long productId) {
        return  productRepository.findById(productId)
            .map(product -> Either.<ResourceNotFoundException, Product>
                right(product))
            .orElseGet(() -> Either.left(new ResourceNotFoundException("ProductId " + productId)));
    }


    public Either<ResourceNotFoundException, Product> findSimilarIds(Long productId) {
      return null;
    }
}