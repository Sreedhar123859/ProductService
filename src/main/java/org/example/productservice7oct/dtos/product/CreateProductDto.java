package org.example.productservice7oct.dtos.product;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice7oct.models.Category;
import org.example.productservice7oct.models.Product;

@Getter
@Setter
public class CreateProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;

    public static CreateProductDto fromProduct(Product product) {
        CreateProductDto response = new CreateProductDto();
        response.setId(product.getId());
        response.setTitle(product.getTitle());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setImageUrl(product.getImageUrl());
        return response;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category c1=new Category();
        c1.setName(this.categoryName);
        product.setCategory(c1);

        return product;
    }
}
