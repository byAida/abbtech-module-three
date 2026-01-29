package com.abbtech.controller;

import com.abbtech.dto.ModelDto;
import com.abbtech.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ModelDto> getAllModels() {
        return modelService.getModels();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModelDto getModelById(@PathVariable Integer id) {
        return modelService.getModelById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addModel(@RequestBody @Valid ModelDto modelDto) {
        modelService.addModel(modelDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateModel(@PathVariable Integer id, @RequestBody @Valid ModelDto modelDto) {
        modelService.updateModel(id, modelDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModel(@PathVariable Integer id) {
        modelService.deleteModel(id);
    }
}
