package com.vgx.product_api.service;

import com.vgx.product_api.model.Product;
import com.vgx.product_api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }

  public Product findProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("Produto não encontrado com o ID: %d", id)));
  }

  public void deleteProductById(Long id) {
    productRepository.deleteById(id);
  }

  public Product updateProduct(Long id, Product product) {
    Product existingProduct = findProductById(id);
    existingProduct.setName(product.getName());
    existingProduct.setDescription(product.getDescription());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setQuantity(product.getQuantity());
    return productRepository.save(existingProduct);
  }

  public Product updateProductQuantity(Long id, Integer quantity) {
    Product existingProduct = findProductById(id);
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantidade não pode ser negativa");
    }
    existingProduct.setQuantity(quantity);
    return productRepository.save(existingProduct);
  }
}