package org.example.productservice7oct.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice7oct.models.Category;
import org.example.productservice7oct.models.Product;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public Product toProduct(){
        Product returnProduct = new Product();
        returnProduct.setId(this.getId());
        returnProduct.setTitle(this.getTitle());
        returnProduct.setDescription(this.getDescription());
        returnProduct.setImageUrl(this.getImage());
        Category c1=new Category();
        c1.setName(this.getCategory());
        returnProduct.setCategory(c1);
        returnProduct.setPrice(this.getPrice());
        return returnProduct;
    }
}
