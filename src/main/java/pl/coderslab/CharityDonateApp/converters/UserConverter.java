package pl.coderslab.CharityDonateApp.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.CharityDonateApp.entities.CharityUser;
import pl.coderslab.CharityDonateApp.models.RegistrationForm;

@Component
public class UserConverter {
    public CharityUser convertRegistrationToUser(RegistrationForm registration) {
        CharityUser charityUser = new CharityUser();
        charityUser.setFirstName(registration.getFirstName());
        charityUser.setLastName(registration.getLastName());
        charityUser.setEmail(registration.getEmail());
        charityUser.setPassword(registration.getPass1());
        return charityUser;

    }
}