package com.test.backenddevtest.services;


import com.test.backenddevtest.domain.exceptions.ResourceNotFoundException;
import com.test.backenddevtest.jpa.ProductRepository;
import com.test.backenddevtest.persistence.entities.Product;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Slf4j
@Service
public class ProductService  {
    @Autowired
    ProductRepository productRepository;

    @Retryable(value = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(delay = 3000, multiplier = 2))
    public Either<ResourceNotFoundException, Product> findById(Long productId) {
        return  productRepository.findById(productId)
            .map(product -> Either.<ResourceNotFoundException, Product>
                right(product))
            .orElseGet(() -> Either.left(new ResourceNotFoundException("ProductId " + productId)));
    }

    @Retryable(value = RuntimeException.class, maxAttempts = 4, backoff = @Backoff(delay = 3000, multiplier = 2))
    public Optional<List <Product>> findSimilarIds(Long productId) {
        Product product = noneNull(findById(productId).getOrNull());
        List<Product> allProducts = productRepository.findAll();

        Stream<Product> integerStream = allProducts.stream().map(this::noneNull)
            .filter(product1 -> product1.getStyle().equals(product.getStyle())
                && product1.getGeneration().equals(product.getGeneration()));
        return Optional.of(integerStream.toList());
    }

    private Product noneNull(Product product){
        return Optional.ofNullable(product).isPresent() ? Product.builder()
            .Id(product.getId() != null ? product.getId() : -1L)
            .name(product.getName() != null ? product.getName() : "n/a")
            .price(product.getPrice() != null ? product.getPrice(): 0)
            .availability(product.getAvailability() != null ? product.getAvailability() : false)
            .generation(product.getGeneration() != null ? product.getGeneration() : "n/a")
            .style(product.getStyle() != null ? product.getStyle() : "n/a")
            .build() : Product.builder().build();
    }

    @Recover
    public void retryFailure(Exception e) {
        log.error("We have exhausted the attempts to retry the read from DB, this is an alert to consider " +
            "for recovery purposes");
    }
}