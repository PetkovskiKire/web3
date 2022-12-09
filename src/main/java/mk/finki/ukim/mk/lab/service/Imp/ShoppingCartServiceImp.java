package mk.finki.ukim.mk.lab.service.Imp;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.exception.OrderNotFoundException;
import mk.finki.ukim.mk.lab.model.exception.ShoppingCartNotFoundException;
import mk.finki.ukim.mk.lab.model.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRep;
import mk.finki.ukim.mk.lab.repository.jpa.UserRep;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {
    private final ShoppingCartRep shoppingCartRep;
    private final UserRep userRep;
    private final OrderService orderService;

    public ShoppingCartServiceImp(ShoppingCartRep shoppingCartRep, UserRep userRep, OrderService orderService) {
        this.shoppingCartRep = shoppingCartRep;
        this.userRep = userRep;
        this.orderService = orderService;
    }

    @Override
    public List<Order> listAllBalloonsInShoppingCart(Long cartId) {
        if (!this.shoppingCartRep.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRep.findById(cartId).get().getOrders();
    }

    @Override
    @UpdateTimestamp
    public ShoppingCart getActiveShoppingCart(String username, LocalDateTime date) {

        User user = (User) this.userRep.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRep.findByUser(user).orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user, date);
                    return this.shoppingCartRep.save(cart);
                });

    }

    @Override
    public ShoppingCart addBalloonToShoppingCart(String username, LocalDateTime date, Long orderId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username, date);
        Order order = this.orderService.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        shoppingCart.getOrders().add(order);
        return this.shoppingCartRep.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findbyId(long cartId) {
        return this.shoppingCartRep.findById(cartId);
    }
}














