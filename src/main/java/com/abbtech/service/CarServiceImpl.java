package com.abbtech.service;

import com.abbtech.dto.CarDetailsDto;
import com.abbtech.dto.CarDto;
import com.abbtech.exception.CarErrorEnum;
import com.abbtech.exception.CarException;
import com.abbtech.model.*;
import com.abbtech.repository.CarRepository;
import com.abbtech.repository.FeatureRepository;
import com.abbtech.repository.ModelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final FeatureRepository featureRepository;

    public CarServiceImpl(CarRepository carRepository, ModelRepository modelRepository,
                          FeatureRepository featureRepository) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.featureRepository = featureRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDto> getCars() {
        return carRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CarDto getCarById(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
        return toDto(car);
    }

    @Override
    @Transactional
    public void addCar(CarDto carDto) {
        Model model = modelRepository.findById(carDto.modelId())
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));

        Car car = Car.builder()
                .vin(carDto.vin())
                .registrationNumber(carDto.registrationNumber())
                .mileageKm(carDto.mileageKm())
                .productionYear(carDto.productionYear())
                .model(model)
                .build();

        if (carDto.carDetails() != null) {
            CarDetails details = new CarDetails();
            details.setCar(car);
            details.setEngineNumber(carDto.carDetails().engineNumber());
            details.setRegistrationCode(carDto.carDetails().registrationCode());
            details.setFuelType(carDto.carDetails().fuelType());
            details.setEngineCapacity(carDto.carDetails().engineCapacity());
            details.setColor(carDto.carDetails().color());
            details.setInsuranceNumber(carDto.carDetails().insuranceNumber());
            car.setCarDetails(details);
        }

        if (carDto.featureIds() != null) {
            List<Feature> features = featureRepository.findAllById(carDto.featureIds());
            car.setFeatures(features);
        }

        carRepository.save(car);
    }

    @Override
    @Transactional
    public void updateCar(Integer id, CarDto carDto) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));

        car.setVin(carDto.vin());
        car.setRegistrationNumber(carDto.registrationNumber());
        car.setMileageKm(carDto.mileageKm());
        car.setProductionYear(carDto.productionYear());

        if (carDto.modelId() != null) {
            Model model = modelRepository.findById(carDto.modelId())
                    .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
            car.setModel(model);
        }

        if (carDto.carDetails() != null) {
            CarDetails details = car.getCarDetails() != null ? car.getCarDetails() : new CarDetails();
            details.setCar(car);
            details.setEngineNumber(carDto.carDetails().engineNumber());
            details.setRegistrationCode(carDto.carDetails().registrationCode());
            details.setFuelType(carDto.carDetails().fuelType());
            details.setEngineCapacity(carDto.carDetails().engineCapacity());
            details.setColor(carDto.carDetails().color());
            details.setInsuranceNumber(carDto.carDetails().insuranceNumber());
            car.setCarDetails(details);
        }

        if (carDto.featureIds() != null) {
            List<Feature> features = featureRepository.findAllById(carDto.featureIds());
            car.setFeatures(features);
        }

        carRepository.save(car);
    }

    @Override
    @Transactional
    public void deleteCar(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarException(CarErrorEnum.CAR_NOT_FOUND));
        carRepository.delete(car);
    }

    private CarDto toDto(Car car) {
        CarDetails details = car.getCarDetails();
        CarDetailsDto detailsDto = details == null ? null : new CarDetailsDto(
                details.getId(),
                details.getEngineNumber(),
                details.getRegistrationCode(),
                details.getFuelType(),
                details.getEngineCapacity(),
                details.getColor(),
                details.getInsuranceNumber()
        );

        List<Integer> featureIds = car.getFeatures() == null ? List.of()
                : car.getFeatures().stream().map(Feature::getId).toList();

        return new CarDto(
                car.getId(),
                car.getVin(),
                car.getRegistrationNumber(),
                car.getMileageKm(),
                car.getProductionYear(),
                car.getModel().getId(),
                detailsDto,
                featureIds
        );
    }
}
