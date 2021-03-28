package pl.coderslab.CharityDonateApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.CharityDonateApp.entities.CharityUser;
import pl.coderslab.CharityDonateApp.entities.Role;
import pl.coderslab.CharityDonateApp.repositories.RoleRepository;
import pl.coderslab.CharityDonateApp.repositories.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements CharityUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<CharityUser> showUsers() {
        return userRepository.findAll();
    }

    @Override
    public CharityUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void addUser(CharityUser user) {
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
