package com.will.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController is instantiating the controller
// @RequestMapping creates an instance of the car controller and through this
// this is our API, it talks to our service. Therefore, we annotate with RestController.
// this prevents us specifying the path on each method


@RestController
public class CarController {

    // injecting carDAO in here
    private CarService carService;

    // because we have used a 'singleton' with the @Component annotation
    // this is automatically injected here
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(path = "cars")
    public void addCar(@RequestBody Car car){
        carService.registerNewCar(car);
    }

    @GetMapping(path = "cars")
    public List<Car> getCars(){
        return carService.getAllCars();
    }

    @GetMapping(path = "cars/{id}")
    public Car getCarById(@PathVariable("id") Integer id){
        return carService.getCarById(id);
    }

    @DeleteMapping(path = "cars/{id}")
    public void deleteCarById(@PathVariable("id") Integer id){
        carService.deleteCar(id);
    }
    @PutMapping(path = "cars/{id}")
    public void updateCarById(@PathVariable("id") Integer carId, @RequestBody Car car){
        carService.updateAllCarDetails(carId, car);
    }
    @PutMapping(path = "cars/detail/{id}")
    public void updateCarDetailById(@PathVariable("id") Integer carId,
                              @RequestParam(required = false) String regNumber,
                              @RequestParam(required = false) String brand,
                              @RequestParam(required = false) Double price){
        carService.updateCarDetail(carId, regNumber, brand, price);
    }
}
