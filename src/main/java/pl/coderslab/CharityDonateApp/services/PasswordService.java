package pl.coderslab.CharityDonateApp.services;

import org.springframework.stereotype.Service;
import pl.coderslab.CharityDonateApp.models.RegistrationForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordService {
    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\-\\=])(?=.*[A-Z])(?!.*\\s).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean arePasswordsSame(String pass1, String pass2) {
        return pass2.equals(pass1);
    }
    public boolean passwordValidator(RegistrationForm registrationForm) {
        String pass1 = registrationForm.getPass1();
        String pass2 = registrationForm.getPass2();
        return pass2!=null && pass1!=null && arePasswordsSame(pass1,pass2) && isPasswordValid(pass1);
    }
}
