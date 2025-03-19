package fr.epsi.springtp3.domain.animals.repositories;

import fr.epsi.springtp3.domain.animals.entities.Animal;
import fr.epsi.springtp3.domain.animals.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
    List<Person> findAllByLastnameOrFirstname(String lastname, String firstname);
    List<Person> findAllByAgeAfter(int age);

    @Query("from Person p where p.age between :ageMin and :ageMax")
    List<Person> findAllByAgeBetween(@Param("ageMin") Integer ageMin, @Param("ageMax") Integer ageMax);

    @Query("from Person p where :animal member of p.animals")
    List<Person> findAllByAnimals(@Param("animal") Animal animal);
}
