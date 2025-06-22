package org.example.productservice7oct.service.sortingService;

import org.example.productservice7oct.dtos.search.SortingCriteria;

public class SortFactory {
    public static Sorter getSorterByCriteria(SortingCriteria criteria) {
        return switch (criteria){
            case PRICE_HIGH_TO_LOWER -> new PriceHighToLowSort();
            case PRICE_LOW_TO_HIGHER -> new PriceLowToHighSort();
            case RELEVANCE -> null;
            case POPULARITY -> null;
            case RATING_HIGH_TO_LOWER -> null;
            case RATING_LOW_TO_HIGHER -> null;
            default -> null;
        };
    }
}
