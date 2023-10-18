package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.repository.InstitutionRepository;

@Service
public class DonationService {
    DonationRepository donationRepository;

    public DonationService(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int count() {
        return (int) donationRepository.count();
    }

    public int sumBagsQuantity() {
        return (int) donationRepository.sumBagsQuantity();
    }

}
