package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRep extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByNameLike(String name);
    List<Balloon> findAllByDescriptionLike(String text);
    void deleteByName(String name);
}
