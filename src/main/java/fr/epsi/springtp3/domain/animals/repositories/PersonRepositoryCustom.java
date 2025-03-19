package fr.epsi.springtp3.domain.animals.repositories;

import fr.epsi.springtp3.domain.animals.entities.Person;
import fr.epsi.springtp3.domain.animals.entities.Species;

import java.util.List;

public interface PersonRepositoryCustom {
    void deleteAllWithNoAnimals();
    List<Person> createMultiple(int count);
}
