package com.will.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

// we want this class instantiated as a bean - @Component
// this is a 'singleton' meaning this same instantiation can be reused by any class
// this way you do not have to instantiate a class everytime
// objects are stored inside the heap
// @Component stores carService in the heap.
// Everything else uses this same reference to access the carService
// the framework handles all of this for us
// idea is known as 'dependency injections'
// We use this same annotation with the DAO
@Service // this should be called @Service for readability
public class CarService {

    private CarDAO carDAO;

    public CarService(@Qualifier("postgres") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void registerNewCar(Car car){
        // business logic, check if reg number is valid and not taken
        if(car.getPrice() <= 0){
            throw new IllegalStateException("Car price cannot be 0 or less");
        }
        int result = carDAO.insertCar(car);

        if (result != 1){
            throw new IllegalArgumentException("Could not save car");
        }
    }

    public List<Car> getAllCars(){
        return carDAO.selectAllCars();
    }

    public Car getCarById(Integer id) {
        Car car = carDAO.selectCarById(id);
        if (car == null){
            throw new NullPointerException("No car with that ID found.");
        }
        return car;
    }

    public void deleteCar(Integer id) {
        int result = carDAO.deleteCar(id);
        if (result != 1){
            throw new RuntimeException("Car could not be deleted.");
        }
    }

    public void updateAllCarDetails(Integer id, Car car) {
        if (getCarById(id) == null){
            throw new IllegalStateException("Non car with that ID found.");
        }
        if (getCarById(id).equals(car)){
            throw new RuntimeException("No changes were submitted.");
        }
        int result = carDAO.updateCar(id, car);
        if (result != 1){
            throw new IllegalStateException("Car could not be updated");
        }
    }

    public void updateCarDetail(Integer carId, String regNumber, String brand, Double price) {
        Car car = carDAO.selectCarById(carId);
        if (regNumber != null && regNumber.length() > 0 && !Objects.equals(car.getRegNumber(), regNumber)){
            car.setRegNumber(regNumber);
        }

        if (brand != null && !Objects.equals(car.getBrand(), brand)){
            try {
                Brand test = Brand.valueOf(brand.toUpperCase());
                car.setBrand(test);
            } catch (IllegalStateException e){
                throw new IllegalStateException("Brand not recognised.");
            }
        }

        if (price != null && price > 0 && !Objects.equals(car.getPrice(), price)){
            car.setPrice(price);
        }
    }
}
