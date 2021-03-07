package ru.geekbrains.service;

import javax.ejb.Local;

@Local
public interface CartService {

    void addToCart(ProductRepr product);

    void deleteProductFromCart(ProductRepr product);
}
