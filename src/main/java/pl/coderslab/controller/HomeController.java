package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.domain.Institution;
import pl.coderslab.services.DonationService;
import pl.coderslab.services.InstitutionService;

import java.util.List;


@Controller
public class HomeController {
    InstitutionService institutionService;
    DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionService.findAll();
        int countDonations = donationService.count();
        int sumBagsQuantity = donationService.sumBagsQuantity();
        model.addAttribute("institutions", institutions);
        model.addAttribute("countDonations", countDonations);
        model.addAttribute("sumBagsQuantity", sumBagsQuantity);
        return "index";
    }


}
