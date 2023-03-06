package com.test.backenddevtest.persistence.initialdata;

import com.test.backenddevtest.jpa.ProductRepository;
import com.test.backenddevtest.persistence.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SetInitialData {

    private static final Logger log = LoggerFactory.getLogger(SetInitialData.class);

    @Bean
    CommandLineRunner populateDateBase(ProductRepository productRepository){
        return args -> {
            Product product = Product.builder()
                .Id(1L)
                .name("Shirt")
                .price(9.99)
                .availability(Boolean.TRUE)
                .generation("upper")
                .style("comfortable")
                .build();

            log.info("Loading data: " + productRepository.save(product));

            Product product2 = Product.builder()
                .Id(2L)
                .name("Dress")
                .price(19.99)
                .availability(Boolean.TRUE)
                //.generation("n/a")
                .style("classic")
                .build();

            log.info("Loading data: " + productRepository.save(product2));

            Product product3 = Product.builder()
                .Id(3L)
                .name("Blazer")
                .price(29.99)
                .availability(Boolean.FALSE)
                .generation("upper")
                .style("classic")
                .build();

            log.info("Loading data: " + productRepository.save(product3));

            Product product4 = Product.builder()
                .Id(4L)
                .name("Boots")
                .price(39.99)
                .availability(Boolean.TRUE)
                .generation("lower")
                .style("comfortable")
                .build();

            log.info("Loading data: " + productRepository.save(product4));


            Product product100 = Product.builder()
                .Id(100L)
                .name("Trousers")
                .price(49.99)
                .availability(Boolean.FALSE)
                .generation("lower")
                .style("comfortable")
                .build();

            log.info("Loading data: " + productRepository.save(product100));

            Product product1000 = Product.builder()
                .Id(1000L)
                .name("Coat")
                .price(89.99)
                .availability(Boolean.FALSE)
                .generation("upper")
                .style("classic")
                .build();

            log.info("Loading data: " + productRepository.save(product1000));

            Product product10000 = Product.builder()
                .Id(10000L)
                .name("Leather jacket")
                .price(89.99)
                .availability(Boolean.TRUE)
                .generation("upper")
                .style("comfortable")
                .build();

            log.info("Loading data: " + productRepository.save(product10000));

        };
    }
}
