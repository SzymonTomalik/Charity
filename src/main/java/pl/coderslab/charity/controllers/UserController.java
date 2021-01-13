package pl.coderslab.charity.controllers;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import pl.coderslab.charity.converters.UserConverter;import pl.coderslab.charity.models.RegistrationForm;import pl.coderslab.charity.services.PasswordService;import pl.coderslab.charity.services.UserService;import javax.validation.Valid;@Controller@Slf4j@RequiredArgsConstructor@RequestMapping("/user")public class UserController {    private final UserService userService;    private final PasswordService passwordService;    private final UserConverter userConverter;    @GetMapping("/form")    public String registrationForm(Model model) {        model.addAttribute("registration", new RegistrationForm());        model.addAttribute("usersList", userService.showUsers());        return "user/register";    }    @PostMapping("/form")    public String processForm(@Valid RegistrationForm registration) {        if (!passwordService.passwordValidator(registration)) {            log.info("Password is incorrect");            return "user/register";        } else {            userService.addUser(userConverter.convertRegistrationToUser(registration));            return "user/register-confirmation";        }    }}