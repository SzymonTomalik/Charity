package pl.coderslab.CharityDonateApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.CharityDonateApp.models.CurrentUser;
import pl.coderslab.CharityDonateApp.services.UserService;

@ControllerAdvice
@RequiredArgsConstructor
public class AttributeAdvice {
    private final UserService userService;


    @ModelAttribute("currentLoggedInUser")
    public void getUserName(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        if (userService.isLogged()) {
            model.addAttribute("currentLoggedInUser", currentUser.getUser().getFirstName());
        }
    }
}
