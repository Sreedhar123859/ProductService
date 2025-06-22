package org.example.productservice7oct.dtos.product;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice7oct.models.Category;
import org.example.productservice7oct.models.Product;

@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private String imageUrl;
    private String categoryName;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        Category c1=new Category();
        c1.setName(categoryName);
        product.setCategory(c1);
        product.setPrice(price);
        return product;
    }
}
