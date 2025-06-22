package org.example.productservice7oct.service.sortingService;

import org.example.productservice7oct.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}
