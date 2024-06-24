package com.taskmaster.controllers.company;

import com.taskmaster.models.company.data.usage.TaxRate;
import com.taskmaster.models.company.data.usage.TaxRateDTO;
import com.taskmaster.models.user.AppUser;
import com.taskmaster.repositories.UserRepository;
import com.taskmaster.services.company.TaxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/taxRates")
public class TaxRateController {

    @Autowired
    private TaxRateService taxRateService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<TaxRateDTO> addTaxRate(Principal principal, @RequestBody TaxRate taxRate) {
        String email = principal.getName();
        TaxRateDTO newTaxRate = taxRateService.addTaxRate(email, taxRate);
        return ResponseEntity.ok(newTaxRate);
    }

    @GetMapping
    public ResponseEntity<List<TaxRateDTO>> getAllTaxRates(Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        List<TaxRateDTO> taxRates = taxRateService.getAllTaxRates(userId);
        return ResponseEntity.ok(taxRates);
    }

    @PutMapping("/{taxRateId}")
    public ResponseEntity<TaxRate> updateTaxRate(Principal principal, @PathVariable Long taxRateId,
            @RequestBody TaxRate taxRate) {
        String email = principal.getName();
        TaxRate updatedTaxRate = taxRateService.updateTaxRate(email, taxRateId, taxRate);
        return ResponseEntity.ok(updatedTaxRate);
    }

    @DeleteMapping("/{taxRateId}")
    public ResponseEntity<Void> deleteTaxRate(Principal principal, @PathVariable Long taxRateId) {
        String email = principal.getName();
        taxRateService.deleteTaxRate(email, taxRateId);
        return ResponseEntity.noContent().build();
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        String email = principal.getName();
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user email"));
        return user.getId();
    }
}
