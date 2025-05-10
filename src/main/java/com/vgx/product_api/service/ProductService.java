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
        .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID:" + id));
  }

  public void deleteProductById(Long id) {
     if (!productRepository.existsById(id)) {
       throw new EntityNotFoundException("Produto com ID " + id + " não encontrado para exclusão.");
     }
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

  public Product decreaseQuantity(Long id) {
    Product existingProduct = findProductById(id);
    if (existingProduct.getQuantity() == 0) {
      throw new IllegalArgumentException("Produto zerado! Não pode ficar com quantidade negativa");
    }
    existingProduct.setQuantity(existingProduct.getQuantity() - 1);
    return productRepository.save(existingProduct);
  }
}