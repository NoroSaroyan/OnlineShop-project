package ru.gb.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gb.onlineshop.MethodUtils;
import ru.gb.onlineshop.entity.User;
import ru.gb.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPanel(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "error/404";
        }

        return "user";
    }

//    private final UserService userService;
//
//    @Autowired
//    public UserController(@Qualifier("userService") UserService service) {
//        this.userService = service;
//    }
//
//    @GetMapping("/registration")
//    public String registration(Model model) {
////TODO check if in return model is neede or not
//        model.addAttribute("userForm", new User());
//
//        return "user/registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//       // userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "user/registration";
//        }
//        userService.save(userForm);
////        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//        return "redirect:/user/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
////        if (securityService.isAuthenticated()) {
////            return "redirect:/";
////        }
//
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "user/login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "index";
//    }
//
//    @GetMapping("/home")
//    public String home(Model model){
//        return "user/home";
//    }
}
