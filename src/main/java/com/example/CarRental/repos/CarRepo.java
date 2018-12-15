package com.example.CarRental.repos;

import com.example.CarRental.domain.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepo extends CrudRepository<Car, Integer> {
    List<Car> findByNameCar(String nameCar);

    @Override
    void deleteById(Integer integer);
}
