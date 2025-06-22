package org.example.productservice7oct.dtos.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private String status;
    private String message;
}
