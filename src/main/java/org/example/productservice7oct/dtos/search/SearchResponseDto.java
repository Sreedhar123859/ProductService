package org.example.productservice7oct.dtos.search;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice7oct.dtos.product.GetProductDto;
import org.springframework.data.domain.Page;


@Getter
@Setter
public class SearchResponseDto {
    private Page<GetProductDto> products;
}
