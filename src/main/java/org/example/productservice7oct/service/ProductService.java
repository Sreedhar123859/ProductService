package org.example.productservice7oct.service;

import org.example.productservice7oct.exceptions.ProductNotFoundException;
import org.example.productservice7oct.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product partialUpdateProduct(Long id, Product product) throws ProductNotFoundException;
}
