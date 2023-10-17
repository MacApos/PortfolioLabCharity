package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.domain.Institution;
import pl.coderslab.repository.DonationRepository;
import pl.coderslab.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {
    InstitutionRepository institutionRepository;
    DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        List<Institution> allInstitutions = institutionRepository.findAll();
        int countDonations = (int) donationRepository.count();
        int sumBagsQuantity = (int) donationRepository.sumBagsQuantity();
        model.addAttribute("allInstitutions", allInstitutions);
        model.addAttribute("countDonations", countDonations);
        model.addAttribute("sumBagsQuantity", sumBagsQuantity);
        return "index";
    }



}
