package com.abbtech.service;

import com.abbtech.dto.ModelDto;

import java.util.List;

public interface ModelService {

    List<ModelDto> getModels();

    ModelDto getModelById(Integer id);

    void addModel(ModelDto modelDto);

    void updateModel(Integer id, ModelDto modelDto);

    void deleteModel(Integer id);
}
