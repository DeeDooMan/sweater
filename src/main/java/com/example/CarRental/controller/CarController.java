package com.example.CarRental.controller;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.User;
import com.example.CarRental.repos.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class CarController {

    @Autowired
    private CarRepo carRepo;


    @GetMapping("/mainCar")
    public String mainCar(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Car> cars;

        if (filter !=null && !filter.isEmpty()) {
            cars = carRepo.findByNameCar(filter);
        }
        else {
            cars = carRepo.findAll();
        }

        model.addAttribute("cars", cars);
        model.addAttribute("filter", filter);
        return "mainCar";
    }

    @PostMapping("/mainCar")
    public String addCar(
            @AuthenticationPrincipal User user,
            @Valid Car car,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        car.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap =
                    ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("car", car);
        } else {
            model.addAttribute("car", null);
            carRepo.save(car);
        }
        Iterable<Car> cars = carRepo.findAll();
        model.addAttribute("cars", cars);
        return "mainCar";
    }

    @GetMapping("/car-list/{user}")
    public String carList(@AuthenticationPrincipal User currentUser,
                          @PathVariable User user,
                          Model model,
                          @RequestParam(required = false) Car car
    ){
        Set<Car> cars = user.getCars();

        model.addAttribute("cars", cars);
        model.addAttribute("car", car);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "carIndex";
    }



    @GetMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carRepo.deleteById(id);
        return "redirect:/mainCar";
    }
}
