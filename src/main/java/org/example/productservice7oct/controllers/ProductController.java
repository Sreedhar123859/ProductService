package org.example.productservice7oct.controllers;

import org.example.productservice7oct.dtos.product.*;
import org.example.productservice7oct.exceptions.ProductNotFoundException;
import org.example.productservice7oct.models.Product;
import org.example.productservice7oct.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    private RestTemplate restTemplate;

    public ProductController(@Qualifier("DBProductService") ProductService productService,RestTemplate restTemplate){
        this.productService = productService;
        this.restTemplate = restTemplate;
    }
    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestHeader("Authorization") String token,@RequestBody CreateProductRequestDto createProductRequestDto) {
        boolean isAuthenticated = restTemplate.getForObject(
                "http://userService27Nov/auth/validate?token=" + token,
                Boolean.class
        );
        if (!isAuthenticated) {
            return null;
        }
        Product product = productService.createProduct(
                createProductRequestDto.toProduct()
        );
        CreateProductResponseDto createProductResponseDto = CreateProductResponseDto.fromProduct(product);
        return createProductResponseDto;
    }

    @GetMapping("")
    public GetAllProductsResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto responseDto = new GetAllProductsResponseDto();
        List<GetProductDto> responseProdcutsDto = new ArrayList<>();
        for(Product product : products){
            GetProductDto getProductDto = GetProductDto.from(product);
            responseProdcutsDto.add(getProductDto);
        }
        responseDto.setProducts(responseProdcutsDto);
        return responseDto;
    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto patchProduct(@PathVariable("id") Long id, @RequestBody CreateProductDto productDto) throws ProductNotFoundException {
        Product product = productService.partialUpdateProduct(id, productDto.toProduct());
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));
        return response;
    }


    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable("id") Long id){
        return "Here is you product :" + id.toString();
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable("id") Long id){
        return "Product deleted"+ id;
    }

}
