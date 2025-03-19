package fr.epsi.springtp3.domain.animals.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "animal")
public class Animal {
    public enum Sex {
        MALE, FEMALE
    }

    @Converter(autoApply = true)
    private static class SexConverter implements AttributeConverter<Sex, String> {
        @Override
        public String convertToDatabaseColumn(Sex attribute) {
            if (attribute == null) {
                return null;
            }
            return switch (attribute) {
                case MALE -> "M";
                case FEMALE -> "F";
            };
        }

        @Override
        public Sex convertToEntityAttribute(String dbData) {
            if (dbData == null) {
                return null;
            }

            return switch (dbData) {
                case "M" -> Sex.MALE;
                case "F" -> Sex.FEMALE;
                default -> null;
            };
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String color;

    @Column(nullable = false, length = 50)
    private String name;

    @Convert(converter = SexConverter.class)
    private Sex sex;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "species_id")
    private Species species;

    @ManyToMany(mappedBy = "animals")
    private Set<Person> persons;

    public Animal() {}

    public Animal(String color, String name, Sex sex, Species species) {
        this.color = color;
        this.name = name;
        this.sex = sex;
        this.species = species;
    }

    public Animal(String color, String name, Sex sex, Species species, Set<Person> persons) {
        this.color = color;
        this.name = name;
        this.sex = sex;
        this.species = species;
        this.persons = persons;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return String.format("Animal[color='%s',name='%s',sex='%s',species='%s']", color != null ? color : "null", name, sex, species);
    }
}
