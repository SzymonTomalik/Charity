package pl.coderslab.CharityDonateApp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.CharityDonateApp.services.DonationService;
import pl.coderslab.CharityDonateApp.services.InstitutionService;
import pl.coderslab.CharityDonateApp.services.UserService;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;


    @RequestMapping("/")
    public String homeAction(Model model){
//        chwilowo przekazuje tylko 4 losowe instytucje z bazy
        model.addAttribute("institutions", institutionService.findRandomFourInstitutions());
        model.addAttribute("quantity", donationService.countQuantityOfDonatedBags());
        model.addAttribute("allDonations", donationService.countDonations());
        if (userService.isLogged()) {
            model.addAttribute("isLogged", true);
        }

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        if (userService.isLogged()) {
            return "index";
        }
        return "login";
    }
}
