package com.will.car;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// you can name this when we have 2 or more implementations @Component("fake")
// when when this is used the syntax would be:
// (@Qualifier("fake") CarDAO carDAO).
// @Repository is better as this is more descriptive, it is accessing our data.
@Repository("fake") // this is now available to any class that needs it.
public class FakeCarDataAccessService implements CarDAO{

//    private JdbcTemplate jdbcTemplate;
//
//    public FakeCarDataAccessService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    private List<Car> db;

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }

    @Override
    public Car selectCarById(Integer id) {
        return db.stream().filter(i -> i.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        db.add(car);
        return 1;
    }

    @Override
    public int deleteCar(Integer id) {
        db.remove(selectCarById(id));
        return 1;
    }

    @Override
    public int updateCar(Integer carId, Car update) {
        for (int i = 0; i < db.size(); i++) {
            if (carId.equals(db.get(i).getId())){
                db.set(i, update);
                return 1;
            }
        }
        return 0;
    }
}
