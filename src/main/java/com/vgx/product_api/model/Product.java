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
  @NotBlank(message = "O nome é obrigatório.")
  private String name;

  @Column(name = "description")
  @NotBlank(message = "A descrição é obrigatória.")
  private String description;

  @Column(name = "price")
  @NotNull(message = "O preço é obrigatório.")
  @DecimalMin(value = "0.00", message = "O preço não pode ser negativo.")
  private BigDecimal price;

  @Column(name = "quantity")
  @NotNull(message = "A quantidade é obrigatória.")
  @Min(value = 0, message = "A quantidade não pode ser negativa.")
  private Integer quantity;
}
