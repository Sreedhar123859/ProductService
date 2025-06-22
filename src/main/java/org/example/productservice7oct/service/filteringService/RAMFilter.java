package org.example.productservice7oct.service.filteringService;

import org.example.productservice7oct.models.Product;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

public class RAMFilter implements Filter {

    @Override
    public List<Product> apply(List<Product> products, List<String> filters) {
        List<Product> ans = new ArrayList<>();
        for (Product product: products) {
            if (product.getDescription().contains("")) {
                ans.add(product);
            }
        }
        return ans;
    }
}
