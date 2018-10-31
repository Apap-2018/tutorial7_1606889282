package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;


public interface CarService {
	Optional<CarModel> getCarDetailById(Long id);
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	List<CarModel> getAllCar();
	void updateCar(long oldId, CarModel newCar);
	
}
