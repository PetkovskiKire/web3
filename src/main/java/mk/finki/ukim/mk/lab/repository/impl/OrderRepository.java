package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, Long cartId){
       //Order order = new Order(balloonColor, balloonSize, clientName, clientAddress);
        //Order order = new Order(balloonColor, balloonSize);
        //DataHolder.orderList.add(order);
        //return Optional.of(order);
        return null;
    }
    public List<Order> findAllOrders(){
        return  DataHolder.orderList;
    }
}
