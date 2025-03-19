package fr.epsi.springtp3.domain.animals.repositories;

import fr.epsi.springtp3.domain.animals.entities.Animal;
import fr.epsi.springtp3.domain.animals.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findAllBySpecies(Species species);
    List<Animal> findAllByColorIn(Collection<String> color);

    @Query("select count(a) from Animal a where a.sex = :sex")
    int findAllBySex(@Param("sex") Animal.Sex sex);

    @Query("select a.persons is not empty from Animal a where a = :animal")
    boolean animalHasOwner(@Param("animal") Animal animal);
}
