package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> placeOrder(String balloonColor, String balloonSize, LocalDateTime date, Long cartId);
    List<Order> findAllOrders();

    Optional<Order> findById(Long orderId);

    Order save(String balloonColor, String balloonSize, LocalDateTime date, Long cartId);

    List<Order> filterTime(LocalDateTime from, LocalDateTime to);
}
