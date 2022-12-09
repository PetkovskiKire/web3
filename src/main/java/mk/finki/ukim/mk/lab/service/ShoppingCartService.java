package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<Order> listAllBalloonsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username, LocalDateTime date);

    ShoppingCart addBalloonToShoppingCart(String username, LocalDateTime date, Long orderId);

    Optional<ShoppingCart> findbyId(long parseLong);
}
