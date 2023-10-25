package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Donation;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.service.DonationService;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public int count() {
        return (int) donationRepository.count();
    }

    public int sumBagsQuantity() {
        Integer sumBagsQuantity = donationRepository.sumBagsQuantity();
        if (sumBagsQuantity == null) {
            return 0;
        }
        return sumBagsQuantity;
    }

    public void save(Donation donation) {
        donationRepository.save(donation);
    }

}
