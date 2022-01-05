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

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(@Qualifier("userService") UserService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:user/login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "user/login";
    }

    @RequestMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)

//            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "user/login";
    }

    //@GetMapping("/form")
    //public String userForm(Model model) {
    //    model.addAttribute("isNew", true);
    //  model.addAttribute("userForm", new User());
    //return "user/form";
    //}

    @GetMapping("/edit/{id}")
    public String userOne(@PathVariable Long id, Model model) {
        model.addAttribute("isNew", false);
        model.addAttribute("userForm", service.findOne(id));
        return "user/edit";
    }

//    @GetMapping(value = "/delete/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public @ResponseBody String userDelete(@PathVariable Long id) {
//        return service.deleteById(id);
//    }
//
//    @PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public @ResponseBody String userAdd(@RequestBody User user, BindingResult result) {
//        if(result.hasErrors()) {
//            return ErrorUtils.customErrors(result.getAllErrors());
//        } else {
//            return service.addUser(user);
//        }
//    }

    @GetMapping("/list/{id}")
    public User findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String userList(Model model, Pageable pageable) {
        Page<User> pages = service.findAll(pageable);
        model.addAttribute("users", pages.getContent());
        MethodUtils.pageModel(model, pages);
        return "/user/list";
    }

    @GetMapping("/refresh")
    public String refreshCache(Model model, Pageable pageable) {
//        service.refreshCache();
        Page<User> pages = service.findAll(pageable);
        model.addAttribute("users", pages.getContent());
        MethodUtils.pageModel(model, pages);
        return "/product/products";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "redirect:/registration";
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            service.save(user);
            return "user/registration";
        }
        return "redirect:/";
    }
}
