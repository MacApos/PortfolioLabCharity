package pl.coderslab.service;

import pl.coderslab.entity.Donation;

import java.util.List;

public interface DonationService {
    int count();

    int sumBagsQuantity();

    void save(Donation donation);

    List<Donation> findAll();

}
