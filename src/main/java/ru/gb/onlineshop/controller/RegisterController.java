package ru.gb.onlineshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.onlineshop.entity.Role;
import ru.gb.onlineshop.entity.User;
import ru.gb.onlineshop.validator.UserValidator;
import ru.gb.onlineshop.service.UserService;

import java.util.Arrays;

@Controller
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public RegisterController(@Qualifier("userService") UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            return "register";
        }
        Role role = new Role();
        role.setName("USER");
        role.setId(1L);
        userForm.setRoles(Arrays.asList(role));
        userService.save(userForm);
        userService.loadUserByUsername(userForm.getEmail());

        return "redirect:/home";
    }
}
