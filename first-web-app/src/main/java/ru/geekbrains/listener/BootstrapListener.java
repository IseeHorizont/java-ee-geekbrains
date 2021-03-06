package ru.geekbrains.listener;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        productRepository.saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        productRepository.saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));

        sce.getServletContext().setAttribute("productRepository", productRepository);

        UserRepository userRepository = new UserRepository();

        userRepository.saveOrUpdate(new User(null, "UserName  1", "email1@mail.ru", "12345"));
        userRepository.saveOrUpdate(new User(null, "UserName  2", "email2@mail.ru", "67890"));
        userRepository.saveOrUpdate(new User(null, "UserName  3", "email3@mail.ru", "11111"));

        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
