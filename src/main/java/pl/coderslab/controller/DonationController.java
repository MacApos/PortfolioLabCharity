package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.domain.Category;
import pl.coderslab.domain.Donation;
import pl.coderslab.domain.Institution;
import pl.coderslab.services.CategoryService;
import pl.coderslab.services.DonationService;
import pl.coderslab.services.InstitutionService;

import java.util.List;

@Controller
public class DonationController {
    CategoryService categoryService;
    InstitutionService institutionService;
    DonationService donationService;

    public DonationController(CategoryService categoryService, InstitutionService institutionService,
                              DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/form")
    public String addDonation(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Institution> institutions = institutionService.findAll();
        Donation donation = new Donation();
        model.addAttribute("categories", categories);
        model.addAttribute("institutions", institutions);
        model.addAttribute("donation", donation);
        return "form";
    }

}
