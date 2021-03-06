package com.example.CarRental.controller;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Message;
import com.example.CarRental.domain.Mod;
import com.example.CarRental.domain.User;
import com.example.CarRental.repos.CarRepo;
import com.example.CarRental.repos.MessageRepo;
import com.example.CarRental.repos.ModRepo;
import com.example.CarRental.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ModRepo modRepo;

    //Спринг ишет upload.path в пропертис и подставляет значение в uploadPath
    @Value("${upload.path}")
    private String uploadPath;

    //Убрали лишнее так как на главной странице ничего не принемаем
    @GetMapping("/")
    public String greeting(
                    String name, Map<String, Object> model) {
                    return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Message> messages;
        Iterable<Car> cars = carRepo.findAll();
        Iterable<Mod> mods = modRepo.findAll();

        if (filter !=null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        }
        //Если пользователь ничего не вводит,
        //или вводит не корректный запрос то выводится весь список
        else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("cars", cars);
        model.addAttribute("mods", mods);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap =
                    ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFile(message, file);
            model.addAttribute("message", null);
            messageRepo.save(message);
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
        }


    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-messages/{user}")
    public  String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ){
        Set<Message> messages = user.getMessages();
        Set<Car> cars = user.getCars();
        Set<Mod> mods = user.getMods();

        model.addAttribute("messages", messages);
        model.addAttribute("cars", cars);
        model.addAttribute("mods", mods);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("mod") String mod,
            @RequestParam("price") String price,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            if (!StringUtils.isEmpty(mod)) {
                message.setMod(mod);
            }

            if (!StringUtils.isEmpty(price)) {
                message.setPrice(price);
            }

            saveFile(message, file);

            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

    @GetMapping("/deleteMessage/{id}")
    public String deleteMessage(@PathVariable("id") int id) {
        messageRepo.deleteById(id);
        return "redirect:/main";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepo.deleteById(id);
        return "redirect:/user";
    }




}