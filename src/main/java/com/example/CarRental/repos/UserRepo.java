package com.example.CarRental.repos;

import com.example.CarRental.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Override
    void deleteById(Long aLong);
}
