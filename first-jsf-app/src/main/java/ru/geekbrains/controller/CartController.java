package ru.geekbrains.controller;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Long, Product> productMap = new HashMap<>();

    public List<Product> getAllCart() {
        return new ArrayList<>(productMap.values());
    }

    public void addToCart(Product product) {
        productMap.put(product.getId(), product);
    }

    public void removeFromCart(Product product) {
        productMap.remove(product.getId());
    }
}
