package fr.epsi.springtp3.domain.animals.repositories;

import fr.epsi.springtp3.domain.animals.entities.Person;
import fr.epsi.springtp3.domain.animals.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public PersonRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void deleteAllWithNoAnimals() {
        Query query = entityManager.createQuery("delete from Person p where p.animals is empty");
        query.executeUpdate();
    }

    public List<Person> createMultiple(int count) {
        String[] firstNames = {
                "Alice",
                "Bob",
                "Charlie",
                "Diana",
                "Ethan",
                "Fiona",
                "George",
                "Hannah",
                "Isaac",
                "Julia",
                "Kevin",
                "Laura"
        };

        String[] lastNames = {
                "Smith",
                "Johnson",
                "Williams",
                "Brown",
                "Jones",
                "Garcia",
                "Miller",
                "Davis",
                "Rodriguez",
                "Martinez",
                "Hernandez",
                "Lopez",
                "Gonzalez",
                "Wilson",
                "Anderson"
        };

        Role role = entityManager.find(Role.class, 1);

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String firstname = firstNames[(int)Math.floor(Math.random() * (firstNames.length - 1))];
            String lastname = lastNames[(int)Math.floor(Math.random() * (lastNames.length - 1))];

            Person person = new Person(
                    (int)Math.floor(Math.random() * 100),
                    firstname,
                    lastname,
                    firstname + "." + lastname + "-" + Math.floor(Math.random() * 10000),
                    "****", role);

            entityManager.persist(person);
            persons.add(person);
        }

        return persons;
    }
}
