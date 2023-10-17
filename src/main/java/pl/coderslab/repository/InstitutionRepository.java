package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.domain.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
