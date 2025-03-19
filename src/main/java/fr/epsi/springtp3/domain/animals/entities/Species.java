package fr.epsi.springtp3.domain.animals.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String commonName;

    @Column(nullable = false)
    private String latinName;

    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL)
    private Set<Animal> animals;

    public Species() {}

    public Species(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
    }

    public String toString() {
        return String.format("%s (%s)", commonName, latinName);
    }

    public int getId() {
        return id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
