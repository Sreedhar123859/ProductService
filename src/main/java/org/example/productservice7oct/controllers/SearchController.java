package org.example.productservice7oct.controllers;

import org.example.productservice7oct.dtos.product.GetProductDto;
import org.example.productservice7oct.dtos.search.FilterDto;
import org.example.productservice7oct.dtos.search.SearchResponseDto;
import org.example.productservice7oct.dtos.search.SortingCriteria;
import org.example.productservice7oct.models.Product;
import org.example.productservice7oct.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping("")
    public SearchResponseDto search(
            @RequestParam("query") String query,
            @RequestParam("filter") List<FilterDto> filters,
            @RequestParam("sortBy") SortingCriteria sortBy,
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("PageSize") Long pageSize
    ) {
        Page<Product> productsPage = searchService.search(
                query,
                filters,
                sortBy,
                pageNumber,
                pageSize
        );

        SearchResponseDto searchResponseDto = new SearchResponseDto();
        List<GetProductDto> getProductDtoList = productsPage.getContent().stream()
                .map(GetProductDto::from)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(productsPage.getNumber(), productsPage.getSize(), productsPage.getSort());
        Page<GetProductDto> getProductDtoPage = new PageImpl<>(getProductDtoList, pageable, productsPage.getSize());
        searchResponseDto.setProducts(getProductDtoPage);
        return searchResponseDto;
    }
}
