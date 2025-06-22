package org.example.productservice7oct.service;

import org.example.productservice7oct.exceptions.ProductNotFoundException;
import org.example.productservice7oct.models.Category;
import org.example.productservice7oct.models.Product;
import org.example.productservice7oct.repositories.CategoryRepository;
import org.example.productservice7oct.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("DBProductService")
public class ProductServiceDBImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(Product product) {
        Category category = categoryInProduct(product);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> productValue = productRepository.findById(id);
        if(productValue.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product productToUpdate = productValue.get();
        if(product.getDescription() != null){
            productToUpdate.setDescription(product.getDescription());
        }
        if(product.getPrice() !=null){
            productToUpdate.setPrice(product.getPrice());
        }
        if(product.getTitle() != null){
            productToUpdate.setTitle(product.getTitle());
        }
        if(product.getImageUrl() != null){
            productToUpdate.setImageUrl(product.getImageUrl());
        }
        if(product.getCategory() != null){
            Category categoryToUpdate = categoryInProduct(product);
            productToUpdate.setCategory(categoryToUpdate);
        }
        return productRepository.save(productToUpdate);
    }

    private Category categoryInProduct(Product product) {
        Optional<Category> category = categoryRepository.findByName(product.getCategory().getName());
        Category toBeInProduct = null;
        if(category.isEmpty()){
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            toBeInProduct = newCategory;
        }
        else{
            toBeInProduct = category.get();
        }
        return toBeInProduct;
    }
}
