package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(String username);

    User save(String username, String k, String p, String s, LocalDate now, List<ShoppingCart> carts);

    Optional<User> findByUsername(String usename);
}
