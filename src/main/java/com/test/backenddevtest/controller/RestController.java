package com.test.backenddevtest.controller;

import com.test.backenddevtest.services.ProductService;
import com.test.backenddevtest.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{productId}")
    @ResponseBody
    public ResponseEntity getProductById(@PathVariable("productId") Long productId) {
      return productService.findById(productId)
          .fold(error -> new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND),
              product -> new ResponseEntity<>(product, HttpStatus.OK));
    }

    @GetMapping("/product/{productId}/similarids")
    @ResponseBody
    public ResponseEntity getSimilarIds(@PathVariable("productId") Long productId) {
      return null;
    }


    @GetMapping("/healthcheck")
    String getOne() {
      return Util.healtCheck();
    }


}
