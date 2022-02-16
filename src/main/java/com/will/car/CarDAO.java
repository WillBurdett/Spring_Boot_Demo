package com.will.car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    Car selectCarById(Integer id);
    List<Car> selectAllCars();
    int insertCar(Car car);
    int deleteCar(Integer id);
    int updateCar(Integer id, Car update);
}
