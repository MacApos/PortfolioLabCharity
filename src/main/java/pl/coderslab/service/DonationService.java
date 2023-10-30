package pl.coderslab.service;

import pl.coderslab.entity.Donation;

import java.util.List;

public interface DonationService {
    int count();

    int sumBagsQuantity();

    List<Donation> findAll();

    Donation findById(Long id);

    void deleteById(Long id);

    void save(Donation donation);


}
