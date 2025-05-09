package com.vgx.product_api.controller;

import com.vgx.product_api.model.Product;
import com.vgx.product_api.service.ProductService;
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
  public ResponseEntity<Page<Product>> getAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "12") int pageSize) {

    List<Product> produtos = productService.findAllProducts();
    int start = Math.min(page * pageSize, produtos.size());
    int end = Math.min(start + pageSize, produtos.size());

    Page<Product> paged = new PageImpl<>(
        produtos.subList(start, end),
        PageRequest.of(page, pageSize),
        produtos.size()
    );

    return ResponseEntity.ok(paged);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.findProductById(id));
  }

  @PostMapping("/create")
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product productCreated =productService.createProduct(product);
    return ResponseEntity.status(201).body(productCreated);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    return ResponseEntity.ok(productService.updateProduct(id, product));
  }

  @PutMapping("/{id}/decrease-quantity")
  public ResponseEntity<Product> decreaseQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
    return ResponseEntity.ok(productService.decreaseQuantity(id, quantity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProductById(id);
    return ResponseEntity.noContent().build();
  }
}
