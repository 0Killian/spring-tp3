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
import java.util.Optional;
import java.util.stream.Collectors;

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
        System.out.println("=== Testing SpeciesRepository ===");

        // Test findFirstByCommonName
        Optional<Species> species = speciesRepository.findFirstByCommonName("Chat");
        System.out.println("findFirstByCommonName('Chat'): " + species.orElse(null));

        // Test findAllByLatinNameContainsIgnoreCase
        List<Species> speciesList = speciesRepository.findAllByLatinNameContainsIgnoreCase("familiaris");
        System.out.println("findAllByLatinNameContainsIgnoreCase('familiaris'): " + speciesList);

        // Test findAllOrderByCommonNameAsc
        List<Species> orderedSpecies = speciesRepository.findAllOrderByCommonNameAsc();
        System.out.println("findAllOrderByCommonNameAsc(): " + orderedSpecies);

        // Test findAllByCommonNameLike
        List<Species> commonNameLike = speciesRepository.findAllByCommonNameLike("Ch%");
        System.out.println("findAllByCommonNameLike('Ch%'): " + commonNameLike);

        System.out.println("\n=== Testing PersonRepository ===");

        // Test findAllByLastnameOrFirstname
        List<Person> persons = personRepository.findAllByLastnameOrFirstname("Lamarque", "Henri");
        System.out.println("findAllByLastnameOrFirstname('Lamarque', 'Henri'): " + persons);

        // Test findAllByAgeAfter
        List<Person> olderPersons = personRepository.findAllByAgeAfter(30);
        System.out.println("findAllByAgeAfter(30): " + olderPersons);

        // Test findAllByAgeBetween
        List<Person> ageRangePersons = personRepository.findAllByAgeBetween(20, 30);
        System.out.println("findAllByAgeBetween(20, 30): " + ageRangePersons);

        // Test findAllByAnimals
        Animal animal = animalRepository.findById(1).orElseThrow();
        List<Person> owners = personRepository.findAllByAnimals(animal);
        System.out.println("findAllByAnimals(animal with id=1): " + owners);

        System.out.println("\n=== Testing AnimalRepository ===");

        // Test findAllBySpecies
        Species speciesChat = speciesRepository.findFirstByCommonName("Chat").orElseThrow();
        List<Animal> animals = animalRepository.findAllBySpecies(speciesChat);
        System.out.println("findAllBySpecies('Chat'): " + animals);

        // Test findAllByColorIn
        List<Animal> coloredAnimals = animalRepository.findAllByColorIn(List.of("Blanc", "Noir"));
        System.out.println("findAllByColorIn(['Blanc', 'Noir']): " + coloredAnimals);

        // Test findAllBySex
        int maleAnimals = animalRepository.findAllBySex(Animal.Sex.MALE);
        System.out.println("findAllBySex('M'): " + maleAnimals);


        // Test animalHasOwner
        boolean hasOwner = animalRepository.animalHasOwner(animal);
        System.out.println("animalHasOwner(animal with id=1): " + hasOwner);
    }
}
