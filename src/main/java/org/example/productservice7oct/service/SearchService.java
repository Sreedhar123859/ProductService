package org.example.productservice7oct.service;

import org.example.productservice7oct.dtos.search.FilterDto;
import org.example.productservice7oct.dtos.search.SortingCriteria;
import org.example.productservice7oct.models.Product;
import org.example.productservice7oct.repositories.ProductRepository;
import org.example.productservice7oct.service.filteringService.FilterFactory;
import org.example.productservice7oct.service.sortingService.SortFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> search(
            String query,
            List<FilterDto> filters,
            SortingCriteria sortBy,
            int pageNumber,
            Long pageSize
    ){
        List<Product> products = productRepository.findByTitleContaining(query);
        for(FilterDto filter : filters) {
            products = FilterFactory.getFilter(filter.getKey()).apply(products,filter.getValues());
        }

        products = SortFactory.getSorterByCriteria(sortBy).sort(products);

        List<Product> productsInPage = new ArrayList<>();

        for(int i = (int) (pageSize * (pageNumber-1)); i<(pageSize * pageNumber) - 1; i++){
            productsInPage.add(products.get(i));
        }

        Pageable pageable = PageRequest.of(pageNumber, Math.toIntExact(pageSize));
        return new PageImpl(productsInPage, pageable, products.size());

    }
}
