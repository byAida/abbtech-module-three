package com.abbtech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModelDto(
        Integer id,
        @NotBlank(message = "Model name cannot be empty") String name,
        @NotBlank(message = "Category cannot be empty") String category,
        @NotNull(message = "Year from cannot be null") Integer yearFrom,
        @NotNull(message = "Year to cannot be null") Integer yearTo,
        @NotNull(message = "Brand id cannot be null") Integer brandId
) {}
