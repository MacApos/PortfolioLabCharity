package pl.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.main.entity.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
