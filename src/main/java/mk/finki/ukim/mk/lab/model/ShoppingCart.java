package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "shoppingCarts", fetch = FetchType.EAGER)
    private List<Order> orders;

    public ShoppingCart(User user, LocalDateTime dateCreated) {
        this.user = user;
        this.dateCreated = dateCreated;
        this.orders =  new ArrayList<>();
    }

    public ShoppingCart() {

    }

}
