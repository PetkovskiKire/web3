package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;
    public String balloonColor;
    public String balloonSize;
    @ManyToOne
    public ShoppingCart shoppingCarts;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date;

    public Order(String balloonColor, String balloonSize,  LocalDateTime date, ShoppingCart shoppingCart) {

        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.shoppingCarts = shoppingCart;
        this.date = date;
    }

    public Order() {

    }
}
