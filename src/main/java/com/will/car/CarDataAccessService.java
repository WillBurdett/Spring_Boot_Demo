package com.will.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// you can name this when we have 2 or more implementations @Component("fake")
// when when this is used the syntax would be:
// (@Qualifier("fake") CarDAO carDAO).
// @Repository is better as this is more descriptive, it is accessing our data.
@Repository("postgres") // this is now available to any class that needs it.
public class CarDataAccessService implements CarDAO{

    private JdbcTemplate jdbcTemplate;

    public CarDataAccessService(JdbcTemplate jdbcTemplate) { // we have the constructor, so it can point to the instance in the heap
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Car> selectAllCars() {
        var sql = """
                SELECT id, regnumber, brand, price
                 FROM car;
                """;
        return jdbcTemplate.query(sql, new CarRowMapper()); // takes the sql and row mapper
    }

//         // THIS IS WHAT THE ABOVE CLASS ACHIEVES USING A ROW MAPPER INSTEAD

//    public List<Car> selectAllCarsAlt() {
//        var sql = """
//                SELECT id, regnumber, brand, price
//                FROM car;
//                """;
//       return jdbcTemplate.query(sql, (rs, i) -> {
//           return new Car(rs.getInt("id"),
//                   rs.getString("regNumber"),
//                   Brand.valueOf(rs.getString("brand")),
//                   rs.getDouble("price")
//           );
//       });
//    }

    @Override
    public Car selectCarById(Integer id) {
        var sql = """
                SELECT id, regnumber, brand, price
                 FROM car
                 WHERE id = ?;
                """;

        for (int i = 0; i < selectAllCars().size(); i++) {
            if (selectAllCars().get(i).getId().equals(id)){
                return selectAllCars().get(i);
            }
        }
        return null;
    }

    @Override
    public int insertCar(Car car) {
        String insertSql = """
                    INSERT INTO car(regnumber, brand, price)
                    VALUES(?,?,?);
                """;
        return jdbcTemplate.update( // returns the number of rows effected
                insertSql,
                car.getRegNumber(), car.getBrand().toString(), car.getPrice());
    }

    @Override
    public int deleteCar(Integer id) {
        var sql = """
                DELETE FROM car
                WHERE id = ?
                """;
       return  jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateCar(Integer carId, Car update) {
        return 0;
    }
}
