package com.abbtech.dto;

import com.abbtech.validation.BrandGroupA;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record ReqBrandDto(
        @NotBlank(message = "Brand name cannot be empty", groups = BrandGroupA.class)
        String name,
        String country,
        @Positive(message = "Founded year must be positive")
        @Min(1900)
        @Max(2100)
        Integer foundedYear,
        @Size(min = 1, max = 2, message = "At least 1 model and max 2 models are allowed")
        @Valid
        List<ModelDto> models
) {}
