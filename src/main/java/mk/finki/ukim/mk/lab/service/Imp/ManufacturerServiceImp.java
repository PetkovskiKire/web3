package mk.finki.ukim.mk.lab.service.Imp;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.jpa.ManufactureRep;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImp  implements ManufacturerService {

    public  final ManufactureRep manufacturerRepository;

    public ManufacturerServiceImp(ManufactureRep manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }
}
