package com.will.car;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet resultset, int i) throws SQLException{
        return new Car(
                resultset.getInt("id"),
                resultset.getString("regnumber"),
                Brand.valueOf(resultset.getString("brand")),
                resultset.getDouble("price")
        );
    }


}
