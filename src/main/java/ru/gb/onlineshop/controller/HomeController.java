package ru.gb.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.onlineshop.entity.Product;
import ru.gb.onlineshop.service.CategoryService;
import ru.gb.onlineshop.service.ProductService;

import java.util.List;

@Controller
//@RequestMapping("/")
public class HomeController {
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    public void setCategoryService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(Model model) {
        model.addAttribute("products", getAllProducts());
        model.addAttribute("productsCount", productsCount());
        model.addAttribute("categories", categoryService.findAll());
        return "home";
    }

    //TODO fix
    @RequestMapping("/searchByProductTitle")
    public String homePost(@RequestParam("productTitle") String title, Model model) {
        model.addAttribute("products", productService.search(title));
        model.addAttribute("products count", productService.count());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    private List<Product> getAllProducts() {
        return productService.findAll();
    }

    private long productsCount() {
        return productService.count();
    }

}
