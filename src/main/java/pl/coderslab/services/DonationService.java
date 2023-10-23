package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.domain.Donation;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.repository.InstitutionRepository;

@Service
public class DonationService {
    DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int count() {
        return (int) donationRepository.count();
    }

    public int sumBagsQuantity() {
        return (int) donationRepository.sumBagsQuantity();
    }

    public void save(Donation donation){
        donationRepository.save(donation);
    }

}
