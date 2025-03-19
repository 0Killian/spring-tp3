package fr.epsi.springtp3.domain.animals.repositories;

import fr.epsi.springtp3.domain.animals.entities.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> findFirstByCommonName(String commonName);
    List<Species> findAllByLatinNameContainsIgnoreCase(String latinName);

    @Query("from Species s order by s.commonName asc")
    List<Species> findAllOrderByCommonNameAsc();

    @Query("from Species s where s.commonName like :commonName")
    List<Species> findAllByCommonNameLike(@Param("commonName") String commonName);
}
