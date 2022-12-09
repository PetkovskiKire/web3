package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    public List<Balloon> findAllBalloons() {
        return DataHolder.balloonLists;
    }

    public Optional<Balloon> findByName(String name) {
        return DataHolder.balloonLists.stream().filter(i -> i.getName().equals(name)).findFirst();
    }
    public void deleteByName(String name){
        DataHolder.balloonLists.removeIf(i -> i.getName().equals(name));
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloonLists.stream().filter(r -> r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloonLists.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        DataHolder.balloonLists.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        DataHolder.balloonLists.removeIf(i -> i.getName().equals(name));
        Balloon balloon = new Balloon(name, description, manufacturer);
        DataHolder.balloonLists.add(balloon);
        return Optional.of(balloon);
    }
}
