package com.taskmaster.models.company.data.usage;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UsageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usageData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractItem> contractItems;

    @OneToMany(mappedBy = "usageData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaxRate> taxRates;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ContractItem> getContractItems() {
        return contractItems;
    }

    public void setContractItems(List<ContractItem> contractItems) {
        this.contractItems = contractItems;
    }

    public List<TaxRate> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(List<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }
}
