package pl.main.service;

import pl.main.entity.CurrentUser;
import pl.main.entity.Donation;
import pl.main.entity.User;

import java.util.List;

public interface DonationService {
    Donation findById(Long id);

    List<Donation> findAllByUser(User user);

    void save(Donation donation);

    void deleteById(Long id);

    int count(User user);

    int sumBagsQuantity(User user);

}
