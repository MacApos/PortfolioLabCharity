package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.domain.Category;
import pl.coderslab.domain.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    long sumBagsQuantity();
}
