package org.example.productservice7oct.service.filteringService;

public class FilterFactory {
    public static Filter getFilter(String key){
        return switch(key) {
            case "RAM" -> new RAMFilter();
            case "Brand" -> new BrandFilter();
            default -> null;
        };
    }
}
