package com.abbtech.service;

import com.abbtech.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> getCars();
    CarDto getCarById(Integer id);
    void addCar(CarDto carDto);
    void updateCar(Integer id, CarDto carDto);
    void deleteCar(Integer id);
}
