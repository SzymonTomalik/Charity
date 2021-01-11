package pl.coderslab.charity.controllers;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import pl.coderslab.charity.entities.Donation;import pl.coderslab.charity.services.CategoryService;import pl.coderslab.charity.services.DonationService;import pl.coderslab.charity.services.InstitutionService;@Controller@RequiredArgsConstructor@Slf4jpublic class DonationController {    private final CategoryService categoryService;    private final InstitutionService institutionService;    private final DonationService donationService;    @GetMapping("/form")    public String homeAction(Model model) {        model.addAttribute("categories", categoryService.findAll());        model.addAttribute("institutions", institutionService.findAll());        model.addAttribute("donation", new Donation());        return "form";    }    @PostMapping("/form")    public String proceedForm(Donation donation) {        donationService.saveDonation(donation);        return "form-confirmation";    }}