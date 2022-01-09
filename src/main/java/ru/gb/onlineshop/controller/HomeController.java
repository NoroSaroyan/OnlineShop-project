package ru.gb.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.onlineshop.entity.Cart;
import ru.gb.service.CartService;
import java.security.Principal;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CartService cartService;

    @Autowired
    public HomeController(@Qualifier("cartService") CartService service) {
        this.cartService = service;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "user/home";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        return "product/products";
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = principal.getName();
        System.out.println(name);
        System.out.println("___________");
        Cart cart = cartService.findUserByUserEmail(name);
        model.addAttribute("cart",cart);
        return "cart/carts";
    }

    @GetMapping("/cart-add")
    public String addToCart(Model model){
        return "/cart/cartToAdd";
    }
}
