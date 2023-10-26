package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Donation;
import pl.coderslab.entity.Institution;
import pl.coderslab.service.CategoryService;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FormController {
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public FormController(CategoryService categoryService, InstitutionService institutionService,
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
        if(results.hasErrors()){
            return "form";
        }
        donationService.save(donation);
        return "formConfirmation";
    }

    @ModelAttribute("categories")
    public List<Category> addCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> addInstitution() {
        return institutionService.findAll();
    }
}
