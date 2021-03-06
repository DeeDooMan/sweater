package com.example.CarRental.controller;

import com.example.CarRental.domain.Role;
import com.example.CarRental.domain.User;
import com.example.CarRental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        //Сообщаем если такой пользователь есть в базе данных
        if (userFromDb != null){
            model.put("message", "Данное имя пользователя не доступно!");
            return "registration";
        }

        user.setActive(true);
        //На вход ожидается коллекция в виде Set,
        //но так как у нас всего одно значение мы можем
        //использовать шорткат, из стандартной библиотеки,
        //который создает set с одним единственным значением
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
        return "redirect:/login";
    }
}
