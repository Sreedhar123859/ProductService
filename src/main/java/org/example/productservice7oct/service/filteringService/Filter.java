package org.example.productservice7oct.service.filteringService;

import org.example.productservice7oct.models.Product;

import java.util.List;

public interface Filter {
    List<Product> apply(List<Product> products, List<String> filters);
}
