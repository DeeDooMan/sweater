package com.example.CarRental.repos;

import com.example.CarRental.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

    @Override
    void deleteById(Integer integer);
}
