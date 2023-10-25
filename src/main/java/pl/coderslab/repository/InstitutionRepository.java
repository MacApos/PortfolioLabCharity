package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
