package com.example.CarRental.controller;

import com.example.CarRental.domain.Mod;
import com.example.CarRental.domain.User;
import com.example.CarRental.repos.ModRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ModelCarController {

    @Autowired
    private ModRepo modRepo;


    @GetMapping("/mainMod")
    public String mainMod(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Mod> mods;

        if (filter !=null && !filter.isEmpty()) {
            mods = modRepo.findByName(filter);
        }
        else {
            mods = modRepo.findAll();
        }

        model.addAttribute("mods", mods);
        model.addAttribute("filter", filter);
        return "mainMod";
    }

    @PostMapping("/mainMod")
    public String addMod(
            @AuthenticationPrincipal User user,
            @Valid Mod mod,
            BindingResult bindingResult,
            Model model
    ) throws IOException {
        mod.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap =
                    ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("mod", mod);
        } else {
            model.addAttribute("mod", null);
            modRepo.save(mod);
        }
        Iterable<Mod> mods = modRepo.findAll();
        model.addAttribute("mods", mods);
        return "mainMod";
    }

    @GetMapping("/mod-list/{user}")
    public String modList(@AuthenticationPrincipal User currentUser,
                          @PathVariable User user,
                          Model model,
                          @RequestParam(required = false) Mod mod
    ){
        Set<Mod> mods = user.getMods();

        model.addAttribute("mods", mods);
        model.addAttribute("mod", mod);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "modIndex";
    }



    @GetMapping("/deleteMod/{id}")
    public String deleteMod(@PathVariable("id") int id) {
        modRepo.deleteById(id);
        return "redirect:/mainMod";
    }
}
