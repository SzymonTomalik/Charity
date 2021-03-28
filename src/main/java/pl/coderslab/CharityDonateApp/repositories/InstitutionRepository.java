package pl.coderslab.CharityDonateApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.CharityDonateApp.entities.Institution;

import java.util.List;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM institutions ORDER BY rand() LIMIT 4;")
    List<Institution> findRandomFourInstitution();
}
