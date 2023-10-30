package pl.coderslab.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.Status;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.service.DonationService;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public int count() {
        return (int) donationRepository.count();
    }

    @Override
    public int sumBagsQuantity() {
        Integer sumBagsQuantity = donationRepository.sumBagsQuantity();
        if (sumBagsQuantity == null) {
            return 0;
        }
        return sumBagsQuantity;
    }

    @Override
    public void save(Donation donation) {
        donation.setStatus(Status.NOT_TAKEN);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAll() {
        Sort sort = Sort.by("status").descending();
        sort = sort.and(Sort.by("pickUpDate").ascending());
        sort = sort.and(Sort.by("OrderDate").ascending());
        return donationRepository.findAll(sort);
    }

    @Override
    public Donation findById(Long id) {
        return donationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        donationRepository.deleteById(id);
    }
}
