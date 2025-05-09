package com.vgx.product_api.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotBlank
  private String name;

  @Column(name = "description")
  @NotBlank
  private String description;

  @Column(name = "price")
  @NotNull
  @DecimalMin("0.00")
  private BigDecimal price;

  @Column(name = "quantity")
  @NotNull
  @Min(0)
  private Integer quantity;
}
