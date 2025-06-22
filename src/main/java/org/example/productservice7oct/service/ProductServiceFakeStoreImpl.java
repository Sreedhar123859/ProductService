package org.example.productservice7oct.service;

import org.example.productservice7oct.dtos.fakestore.FakeStoreCreateProductRequestDto;
import org.example.productservice7oct.dtos.fakestore.FakeStoreGetProductResponseDto;
import org.example.productservice7oct.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
public class ProductServiceFakeStoreImpl implements ProductService{

    private RestTemplate restTemplate;
    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto requestDto = new FakeStoreCreateProductRequestDto();
        requestDto.setCategory(product.getCategory().getName());
        requestDto.setImage(product.getImageUrl());
        requestDto.setPrice(product.getPrice());
        requestDto.setTitle(product.getTitle());
        requestDto.setDescription(product.getDescription());
        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                requestDto,
                FakeStoreGetProductResponseDto.class
        );

        return response.toProduct();
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) {
        HttpEntity<FakeStoreCreateProductRequestDto> requestEntity = new HttpEntity<>(FakeStoreCreateProductRequestDto.from(product));
        ResponseEntity<FakeStoreGetProductResponseDto> responseEntity  = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH,
                requestEntity,
                FakeStoreGetProductResponseDto.class
        );
        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        throw new RuntimeException();
//        FakeStoreGetProductResponseDto[] fakeStoreResponse = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreGetProductResponseDto[].class
//        );
//        List<FakeStoreGetProductResponseDto> fakeStoreResponseList = Stream.of(fakeStoreResponse).toList();
//        List<Product> products = new ArrayList<>();
//        for(FakeStoreGetProductResponseDto fakeStoreResponseDto : fakeStoreResponseList) {
//            products.add(fakeStoreResponseDto.toProduct());
//        }
//        return products;
    }


}
