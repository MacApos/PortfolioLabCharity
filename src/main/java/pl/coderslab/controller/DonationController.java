package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.domain.Category;
import pl.coderslab.domain.Donation;
import pl.coderslab.domain.Institution;
import pl.coderslab.services.CategoryService;
import pl.coderslab.services.DonationService;
import pl.coderslab.services.InstitutionService;

import javax.validation.Valid;
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

    @GetMapping("/form")
    public String addDonation(Model model) {
        Donation donation = new Donation();
        model.addAttribute("donation", donation);
        return "form";
    }

    @PostMapping("/form")
    public String addDonation(@Valid Donation donation, BindingResult results, Model model) {
        model.addAttribute("donation", donation);
        return "form";
    }

    @ModelAttribute("categories")
    public List<Category> addCategories(){
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitution(){
        return institutionService.findAll();
    }
}
