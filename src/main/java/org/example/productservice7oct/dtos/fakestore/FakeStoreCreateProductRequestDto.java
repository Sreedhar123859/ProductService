package org.example.productservice7oct.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice7oct.models.Product;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public static FakeStoreCreateProductRequestDto from(Product product) {
        FakeStoreCreateProductRequestDto dto = new FakeStoreCreateProductRequestDto();
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory().getName());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImageUrl());
        return dto;
    }
}
