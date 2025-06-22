package org.example.productservice7oct.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;

    @OneToMany
    private List<Product> featuredProducts;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Product> allProducts;

    @OneToOne
    private Subcategory subcategory;

    private int countOfProducts;
}
