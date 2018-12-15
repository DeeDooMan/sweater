package com.example.CarRental.repos;

import com.example.CarRental.domain.Mod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModRepo extends CrudRepository<Mod, Integer> {

    List<Mod> findByName(String name);

    @Override
    void deleteById(Integer integer);
}
