package org.example.productservice7oct.service.filteringService;

import org.example.productservice7oct.models.Product;

import java.util.List;

public class BrandFilter implements Filter {

    public List<Product> apply(List<Product> products, List<String> filters){
        return List.of();
    }
}
