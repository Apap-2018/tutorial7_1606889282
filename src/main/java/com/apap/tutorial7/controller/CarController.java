package com.apap.tutorial7.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	
	@PostMapping("/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}

	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		return carService.getCarDetailById(carId).get();
	}

	@DeleteMapping(value = "/{carId}")
	private String deleteCar(@PathVariable("carId") long id, Model model) {
		CarModel deleteCar = carService.getCarDetailById(id).get();
		carService.deleteCar(deleteCar);
		return "Car has been deleted";
	}

	@PutMapping(value = "/{carId}")
	private String updateCarSubmit(
			@PathVariable (value = "carId") long carId,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") long price,
			@RequestParam("amount") Integer amount,
			@RequestParam("dealerId") long dealerId) {
		CarModel car = (CarModel) carService.getCarDetailById(carId).get();
		if(car.equals(null)) {
			return "Couldn't find your car";
			}
		car.setAmount(amount);;
		car.setBrand(brand);
		car.setPrice(price);
		car.setType(type);
		carService.updateCar(carId, car);
		return " car update success";
		}

	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		return carService.getAllCar();
	}
	
}
