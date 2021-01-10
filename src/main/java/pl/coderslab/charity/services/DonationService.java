package pl.coderslab.charity.services;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import pl.coderslab.charity.entities.Donation;import pl.coderslab.charity.repositories.DonationRepository;@Service@Slf4j@RequiredArgsConstructorpublic class DonationService {    private final DonationRepository donationRepository;    public Long countQuantityOfDonatedBags() {        Long count = donationRepository.countAllQuantities();        if (count == null) {            return 0L;        }        return count;    }    public long countDonations() {        return donationRepository.count();    }    public void saveDonation(Donation donation) {        donationRepository.save(donation);        log.info(donation+" was added successfully");    }}