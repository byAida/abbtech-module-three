package com.abbtech.controller;

import com.abbtech.dto.ReqBrandDto;
import com.abbtech.dto.RespBrandDto;
import com.abbtech.service.BrandService;
import com.abbtech.validation.BrandGroupA;
import com.abbtech.validation.BrandGroupB;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RespBrandDto> getBrands() {
        return brandService.getBrands();
    }

    @GetMapping("/by-id")
    @ResponseStatus(HttpStatus.OK)
    public RespBrandDto getBrandById(@RequestParam("id") Integer id) {
        return brandService.getBrandById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBrand(@RequestBody @Validated(BrandGroupA.class) @Valid ReqBrandDto brandDto) {
        brandService.addBrand(brandDto);
    }

    @PostMapping("/groupb")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBrandGroupB(@RequestBody @Validated(BrandGroupB.class) @Valid ReqBrandDto brandDto) {
        brandService.addBrand(brandDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable int id) {
        brandService.deleteBrandById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBrand(@PathVariable int id, @RequestBody @Valid ReqBrandDto brandDto) {
        brandService.updateBrand(id, brandDto);
    }
}
