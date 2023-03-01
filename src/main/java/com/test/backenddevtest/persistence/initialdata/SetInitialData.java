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
                .mame("Shirt")
                .prices(9.99)
                .availability(Boolean.TRUE).build();

            log.info("Loading data: " + productRepository.save(product));

            Product product2 = Product.builder()
                .Id(2L)
                .mame("Dress")
                .prices(19.99)
                .availability(Boolean.TRUE).build();

            log.info("Loading data: " + productRepository.save(product2));

            Product product3 = Product.builder()
                .Id(3L)
                .mame("Blazer ")
                .prices(29.99)
                .availability(Boolean.FALSE).build();

            log.info("Loading data: " + productRepository.save(product3));

            Product product4 = Product.builder()
                .Id(4L)
                .mame("Boots")
                .prices(39.99)
                .availability(Boolean.TRUE).build();

            log.info("Loading data: " + productRepository.save(product4));

        };
    }
}
