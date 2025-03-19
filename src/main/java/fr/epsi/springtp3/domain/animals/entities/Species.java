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

    public Species(String common_name, String latin_name) {
        this.common_name = common_name;
        this.latin_name = latin_name;
    }

    public String toString() {
        return String.format("%s (%s)", common_name, latin_name);
    }

    public int getId() {
        return id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getLatin_name() {
        return latin_name;
    }

    public void setLatin_name(String latin_name) {
        this.latin_name = latin_name;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
}
