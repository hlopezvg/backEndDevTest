package com.test.backenddevtest.persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;


@Entity
@Table(name = "product")
@AllArgsConstructor
@Builder
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;
  private String mame;
  private Double prices;
  private Boolean availability;

  public Product() {}
}
