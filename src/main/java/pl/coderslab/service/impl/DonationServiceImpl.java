package pl.coderslab.service.impl;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.Status;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.service.DonationService;

import java.util.ArrayList;
import java.util.List;

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
        donation.setStatus(Status.NOT_TAKEN);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAll() {
        List<Order> orders = new ArrayList<>();
        Order status = new Order(Sort.Direction.ASC, "status");
        orders.add(status);

//        Order pickUpDate = new Order(Sort.Direction.ASC, "pick_up_date");
//        orders.add(pickUpDate);

        return donationRepository.findAll(Sort.by("status","pickUpDate"));
    }

}
