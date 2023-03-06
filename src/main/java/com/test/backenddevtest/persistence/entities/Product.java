package com.test.backenddevtest.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "product")
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder=true)
@Getter
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long Id;
  private String name;
  private Double price;
  private Boolean availability;
  //This is just an idea for adding some extra attributes for building some logic that will
  //help us to identify some similarities.
  //https://medium.com/webinterpret-tech/finding-similarity-between-products-27e67caf308
  //https://www.intelistyle.com/what-are-product-attributes-and-why-do-they-matter/
  private String generation; //Lower or Upper
  private String style; //Classic or Comfortable
}
