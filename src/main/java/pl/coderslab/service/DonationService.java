package pl.coderslab.service;

import pl.coderslab.entity.Donation;

public interface DonationService {
    int count();

    int sumBagsQuantity();

    void save(Donation donation);
}
