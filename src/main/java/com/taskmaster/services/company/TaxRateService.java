package com.taskmaster.services.company;

import com.taskmaster.models.company.data.usage.TaxRate;
import com.taskmaster.models.company.data.usage.TaxRateDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.TaxRateRepository;
import com.taskmaster.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxRateService {

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private UserRepository userRepository;

    public TaxRateDTO addTaxRate(String email, TaxRate taxRate) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        taxRate.setUser(user);
        TaxRate savedTaxRate = taxRateRepository.save(taxRate);
        return new TaxRateDTO(savedTaxRate);
    }

    public List<TaxRateDTO> getAllTaxRates(Long userId) {
        List<TaxRate> taxRates = taxRateRepository.findByUserId(userId);
        return taxRates.stream().map(TaxRateDTO::new).collect(Collectors.toList());
    }

    public TaxRate updateTaxRate(String email, Long taxRateId, TaxRate taxRateUpdates) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        TaxRate existingTaxRate = taxRateRepository.findByUserId(user.getId()).stream()
                .filter(taxRate -> taxRate.getId().equals(taxRateId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("TaxRate not found for this user"));

        if (taxRateUpdates.getTitle() != null) {
            existingTaxRate.setTitle(taxRateUpdates.getTitle());
        }
        if (taxRateUpdates.getPercentage() != 0) {
            existingTaxRate.setPercentage(taxRateUpdates.getPercentage());
        }

        return taxRateRepository.save(existingTaxRate);
    }

    public void deleteTaxRate(String email, Long taxRateId) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        TaxRate taxRate = taxRateRepository.findById(taxRateId)
                .orElseThrow(() -> new IllegalArgumentException("TaxRate not found"));
        if (!taxRate.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("TaxRate does not belong to the user");
        }
        taxRateRepository.delete(taxRate);
    }
}
