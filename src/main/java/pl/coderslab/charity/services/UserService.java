package pl.coderslab.charity.services;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;import pl.coderslab.charity.entities.CharityUser;import pl.coderslab.charity.repositories.UserRepository;import java.util.List;@Service@RequiredArgsConstructorpublic class UserService {    private final UserRepository userRepository;    public List<CharityUser> showUsers() {        return userRepository.findAll();    }    public void addUser(CharityUser charityUser) {        userRepository.save(charityUser);    }    public boolean isEmailUnique(String email) {        return userRepository.existsByEmail(email);    }}