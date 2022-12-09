package mk.finki.ukim.mk.lab.service.Imp;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.exception.ManufacturerNotFoundException;
import mk.finki.ukim.mk.lab.repository.jpa.BalloonRep;
import mk.finki.ukim.mk.lab.repository.jpa.ManufactureRep;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImp implements BalloonService {

    public final BalloonRep balloonRepository;
    public final ManufactureRep manufacturerRepository;

    public BalloonServiceImp(BalloonRep balloonRepository, ManufactureRep manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return  this.balloonRepository.findAll();
    }


    @Override
    public List<Balloon> findByName(String name) {
        return this.balloonRepository.findAllByNameLike(name);
    }

    @Override
    public Optional<Balloon> findById(Long Id) {
        return this.balloonRepository.findById(Id);
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId) {
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.balloonRepository.deleteByName(name);
        //return Optional.of(this.balloonRepository.save(name, description, manufacturer));
        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer)));
    }


    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByDescriptionLike(text);
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }
}
