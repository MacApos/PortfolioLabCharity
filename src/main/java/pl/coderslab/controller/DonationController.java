package pl.coderslab.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.User;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
public class DonationController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public DonationController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("/show-donations")
    public String showDonations(Model model) {
        List<Donation> donations = donationService.findAll();
        model.addAttribute("donations", donations);
        return "showDonations";
    }
}
