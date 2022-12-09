package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    @Column(length = 4000)
    public String description;
    @ManyToOne
    public Manufacturer manufacturer;


    public Balloon(String name, String description, Manufacturer manufacturer) {

        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Balloon() {

    }
}
