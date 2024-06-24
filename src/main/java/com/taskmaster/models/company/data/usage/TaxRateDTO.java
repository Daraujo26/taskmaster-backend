package com.taskmaster.models.company.data.usage;

public class TaxRateDTO {

    private Long id;
    private String title;
    private double percentage;
    private Long userId;

    // Default constructor
    public TaxRateDTO() {
    }

    // Parameterized constructor
    public TaxRateDTO(TaxRate taxRate) {
        this.id = taxRate.getId();
        this.title = taxRate.getTitle();
        this.percentage = taxRate.getPercentage();
        this.userId = taxRate.getUser().getId();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
