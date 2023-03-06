package com.test.backenddevtest.controller;

import com.test.backenddevtest.persistence.entities.Product;
import com.test.backenddevtest.services.ProductService;
import com.test.backenddevtest.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Slf4j
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

   @GetMapping("/product/{productId}/similar")
    @ResponseBody
    public List<Product> getSimilarIds(@PathVariable("productId") Long productId) {
      return productService.findSimilarIds(productId).orElse(Collections.emptyList());
    }

    /*@GetMapping("/product/{productId}/similar")
      public Callable<List<Product>> getSimilarIds(@PathVariable("productId") Long productId) {
          return () -> productService.findSimilarIds(productId)
              .orElse(Collections.emptyList());
    }*/


    /*@GetMapping(value = "/product/{productId}/similar", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Product>> getSmimilarAsStreams(@PathVariable("productId") Long productId){
      return Flux.fromStream(Stream.generate(() ->
          productService.findSimilarIds(productId).orElse(Collections.emptyList()))
          .peek((msg) -> {
            log.info(String.valueOf(msg));
          }));
          //return productService.findSimilarIds(productId).orElse(Collections.emptyList());
    }*/

  @GetMapping(value = "/product/{productId}/similar", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<List<Product>> getSimilarAsStreamsFlux(@PathVariable("productId") Long productId){
    return Flux.generate(() -> 97, (state, sink) -> {
      List<Product> value = productService.findSimilarIds(productId).orElse(Collections.emptyList());
      sink.next(value);
      if (value.size() == 0) {
        sink.complete();
      }
      return state + 1;
    });
  }


    @GetMapping("/healthcheck")
    String getOne() {
      return Util.healtCheck();
    }

}
