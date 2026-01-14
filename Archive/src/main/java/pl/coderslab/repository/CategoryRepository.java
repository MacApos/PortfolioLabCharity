package pl.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.main.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
