package ru.geekbrains.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{

    @EJB
    private final Map<Long, ProductRepr> productMap = new HashMap<>();

    @TransactionAttribute
    @Override
    public void addToCart(ProductRepr product) {
        productMap.put(product.getId(), product);
    }

    @TransactionAttribute
    @Override
    public void deleteProductFromCart(ProductRepr product) {
        productMap.remove(product.getId());
    }
}
