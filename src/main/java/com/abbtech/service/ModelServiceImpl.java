package com.abbtech.service;

import com.abbtech.dto.ModelDto;
import com.abbtech.exception.CarErrorEnum;
import com.abbtech.exception.CarException;
import com.abbtech.model.Brand;
import com.abbtech.model.Model;
import com.abbtech.repository.BrandRepository;
import com.abbtech.repository.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ModelDto> getModels() {
        return modelRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ModelDto getModelById(Integer id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
        return toDto(model);
    }

    @Override
    @Transactional
    public void addModel(ModelDto modelDto) {
        Brand brand = brandRepository.findById(modelDto.brandId())
                .orElseThrow(() -> new CarException(CarErrorEnum.BRAND_NOT_FOUND));

        Model model = Model.builder()
                .name(modelDto.name())
                .category(modelDto.category())
                .yearFrom(modelDto.yearFrom())
                .yearTo(modelDto.yearTo())
                .brand(brand)
                .build();

        modelRepository.save(model);
    }

    @Override
    @Transactional
    public void updateModel(Integer id, ModelDto modelDto) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));

        Brand brand = brandRepository.findById(modelDto.brandId())
                .orElseThrow(() -> new CarException(CarErrorEnum.BRAND_NOT_FOUND));

        model.setName(modelDto.name());
        model.setCategory(modelDto.category());
        model.setYearFrom(modelDto.yearFrom());
        model.setYearTo(modelDto.yearTo());
        model.setBrand(brand);

        modelRepository.save(model);
    }

    @Override
    @Transactional
    public void deleteModel(Integer id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
        modelRepository.delete(model);
    }

    private ModelDto toDto(Model model) {
        return new ModelDto(
                model.getId(),
                model.getName(),
                model.getCategory(),
                model.getYearFrom(),
                model.getYearTo(),
                model.getBrand().getId() 
        );
    }
}
