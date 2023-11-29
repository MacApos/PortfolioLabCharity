package pl.coderslab.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Deleted;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.Status;
import pl.coderslab.entity.User;
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
    public List<Donation> findAllByUser(User user) {
        Sort sort = Sort.by("status").ascending()
                .and(Sort.by("pickUpDate").ascending())
                .and(Sort.by("pickUpTime").ascending())
                .and(Sort.by("orderDate").ascending());
        return donationRepository.findAllByUser(user, sort);
    }

    @Override
    public Donation findById(Long id) {
        return donationRepository.findByIdAndDeleted(id, Deleted.AVAILABLE)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Donation donation = findById(id);
        donation.setDeleted(Deleted.DELETED);
        donationRepository.save(donation);
    }
}
