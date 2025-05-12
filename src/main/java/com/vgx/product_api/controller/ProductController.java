package com.vgx.product_api.controller;

import com.vgx.product_api.model.Product;
import com.vgx.product_api.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<?> getAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int pageSize) {
    try {
      List<Product> produtos = productService.findAllProducts();
      int start = Math.min(page * pageSize, produtos.size());
      int end = Math.min(start + pageSize, produtos.size());

      Page<Product> paged = new PageImpl<>(
          produtos.subList(start, end),
          PageRequest.of(page, pageSize),
          produtos.size()
      );

      return ResponseEntity.ok(paged);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Erro ao listar produtos: " + e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    try {
      Product product = productService.findProductById(id);
      return ResponseEntity.ok(product);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(404).body("Produto com ID " + id + " não encontrado.");
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Erro ao buscar produto: " + e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody @Valid Product product) {
    try {
      Product created = productService.createProduct(product);
      return ResponseEntity.status(201).body(created);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Erro ao criar produto: " + e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid Product product) {
    try {
      Product updated = productService.updateProduct(id, product);
      return ResponseEntity.ok(updated);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Erro ao atualizar produto: " + e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
      productService.deleteProductById(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Erro ao excluir produto: " + e.getMessage());
    }
  }

  @PutMapping("/{id}/decrease-quantity")
  public ResponseEntity<?> decreaseQuantity(@PathVariable Long id) {
    try {
      Product updated = productService.decreaseQuantity(id);
      return ResponseEntity.ok(updated);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body("Erro ao decrementar quantidade: " + e.getMessage());
    }
  }
}
