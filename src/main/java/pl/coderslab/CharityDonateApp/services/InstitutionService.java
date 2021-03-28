package pl.coderslab.CharityDonateApp.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.CharityDonateApp.entities.Institution;
import pl.coderslab.CharityDonateApp.repositories.InstitutionRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public List<Institution> findRandomFourInstitutions() {
        return institutionRepository.findRandomFourInstitution();
    }
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }
}
