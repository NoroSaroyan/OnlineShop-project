package ru.gb.onlineshop.service;

import org.springframework.stereotype.Service;
import ru.gb.onlineshop.entity.Cart;
import ru.gb.onlineshop.entity.Product;
import ru.gb.onlineshop.repository.CartRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service("cartService")
public class CartService {
    private Map<Product, Integer> cart = new LinkedHashMap<>();

    private CartRepository cartRepository;

    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart findUserByUserEmail(String email) {
        Optional<Cart> cart = cartRepository.findByUserEmail(email);
        return cart.orElseGet(Cart::new);
    }

    public void addProduct(Product product) {
        if (cart.containsKey(product)) {
            cart.replace(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
    }

    public void removeProduct(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1)
                cart.replace(product, cart.get(product) - 1);
            else if (cart.get(product) == 1) {
                cart.remove(product);
            }
        }
    }

    public void clearProducts() {
        cart.clear();
    }

//    public Map<Product, Integer> productsInCart() {
//        return Collections.unmodifiableMap(cart);
//    }

    public BigDecimal totalPrice() {
        return cart.entrySet().stream()
                .map(k -> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public void cartCheckout() {
        cart.clear();
        // Normally there would be payment etc.
    }

    public Map<Product, Integer> productsInCart() {
        return Collections.unmodifiableMap(cart);
    }

}
