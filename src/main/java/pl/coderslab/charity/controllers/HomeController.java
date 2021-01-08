package pl.coderslab.charity.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.services.DonationService;
import pl.coderslab.charity.services.InstitutionService;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;


    @RequestMapping("/")
    public String homeAction(Model model){
//        chwilowo przekazuje tylko 4 losowe instytucje z bazy
        model.addAttribute("institutions", institutionService.findRandomFourInstitutions());
        model.addAttribute("quantity", donationService.countQuantityOfDonatedBags());
        model.addAttribute("allDonations", donationService.countDonations());

        return "index";
    }
}
