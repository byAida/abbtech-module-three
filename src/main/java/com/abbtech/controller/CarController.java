package com.abbtech.controller;

import com.abbtech.dto.CarDto;
import com.abbtech.service.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDto> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Integer id) {
        return carService.getCarById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody @Valid CarDto carDto) {
        carService.addCar(carDto);
    }

    @PutMapping("/{id}")
    public void updateCar(@PathVariable Integer id, @RequestBody @Valid CarDto carDto) {
        carService.updateCar(id, carDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
    }
}
