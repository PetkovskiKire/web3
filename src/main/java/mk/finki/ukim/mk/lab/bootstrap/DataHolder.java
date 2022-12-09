package mk.finki.ukim.mk.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Balloon> balloonLists = new ArrayList<>();
    public static List<Order>  orderList = new ArrayList<>();
    public static List<Manufacturer>  manufacturerList = new ArrayList<>();

    @PostConstruct
    public void init(){
        Manufacturer manufacturer1 = new Manufacturer("First", "country", "str.1");
        Manufacturer manufacturer2 = new Manufacturer("Second", "country", "str.2");
        Manufacturer manufacturer3 = new Manufacturer("Тhird", "country", "str.3");

        balloonLists.add( new Balloon("Red", "small", manufacturer1));
        balloonLists.add( new Balloon("red", "medium", manufacturer2));
        balloonLists.add( new Balloon("red", "big", manufacturer3));
        balloonLists.add( new Balloon("blue", "small", manufacturer1));
        balloonLists.add( new Balloon("blue", "medium", manufacturer1));
        balloonLists.add( new Balloon("blue", "big", manufacturer2));
        balloonLists.add( new Balloon("yellow", "small", manufacturer2));
        balloonLists.add( new Balloon("green", "medium", manufacturer3));
        balloonLists.add( new Balloon("orange", "big", manufacturer3));
        balloonLists.add( new Balloon("white", "big", manufacturer3));

        //orderList.add(new Order("Red", "big", "1", "11"));
        //orderList.add(new Order("Red", "medium", "2", "22"));
        //orderList.add(new Order("Blue", "small", "3", "33"));


        manufacturerList.add(manufacturer1);
        manufacturerList.add(manufacturer2);
        manufacturerList.add(manufacturer3);


    }


}
