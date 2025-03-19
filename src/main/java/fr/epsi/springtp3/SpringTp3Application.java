package fr.epsi.springtp3;

import fr.epsi.springtp3.domain.animals.entities.Animal;
import fr.epsi.springtp3.domain.animals.entities.Person;
import fr.epsi.springtp3.domain.animals.entities.Role;
import fr.epsi.springtp3.domain.animals.entities.Species;
import fr.epsi.springtp3.domain.animals.repositories.AnimalRepository;
import fr.epsi.springtp3.domain.animals.repositories.PersonRepository;
import fr.epsi.springtp3.domain.animals.repositories.RoleRepository;
import fr.epsi.springtp3.domain.animals.repositories.SpeciesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringTp3Application implements CommandLineRunner {

    private final SpeciesRepository speciesRepository;
    private final AnimalRepository animalRepository;
    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    @Autowired
    public SpringTp3Application(
            SpeciesRepository speciesRepository,
            AnimalRepository animalRepository,
            RoleRepository roleRepository,
            PersonRepository personRepository
    ) {
        this.speciesRepository = speciesRepository;
        this.animalRepository = animalRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringTp3Application.class, args);
    }

    @Transactional
    public void run(String... args) throws Exception {

        this.speciesRepository.save(new Species("Red Fox", "Vulpes vulpes"));
        this.animalRepository.save(new Animal("Golden", "Luna", Animal.Sex.FEMALE, this.speciesRepository.findById(2).get()));
        Role role1 = this.roleRepository.save(new Role("ROLE_INVITE"));
        this.personRepository.save(new Person(25, "Alex", "Carter", "alex.carter" + Math.floor(Math.random() * 1000), "P@ssw0rd123!", this.roleRepository.findById(1).get()));

        List<Species> species = this.speciesRepository.findAll();
        List<Animal> animals = this.animalRepository.findAll();
        List<Role> roles = this.roleRepository.findAll();
        List<Person> persons = this.personRepository.findAll();

        Species species1 = this.speciesRepository.findById(1).get();
        Animal animal = this.animalRepository.findById(1).get();
        Role role = this.roleRepository.findById(1).get();
        Person person = this.personRepository.findById(1).get();

        this.speciesRepository.delete(species1);
        this.animalRepository.delete(animal);
        this.roleRepository.delete(role1);
        this.personRepository.delete(person);

        for (Species s : species) {
            System.out.println(s);
        }

        for (Animal a : animals) {
            System.out.println(a);
        }

        for (Role r : roles) {
            System.out.println(r);
        }

        for (Person p : persons) {
            System.out.println(p);
        }

        System.out.println(species1);
        System.out.println(animal);
        System.out.println(role);
        System.out.println(person);

        System.out.println(this.speciesRepository.count());
        System.out.println(this.animalRepository.count());
        System.out.println(this.roleRepository.count());
        System.out.println(this.personRepository.count());
    }
}
