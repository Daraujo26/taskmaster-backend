package com.taskmaster.models.company.data.usage;

import java.util.List;

public class ContractItemDTO {

    private Long id;
    private String itemClass;
    private String itemName;
    private String itemMessage;
    private Double itemPrice;
    private List<String> images;
    private Boolean chargeTax;
    private Boolean optional;
    private Long userId;

    public ContractItemDTO() {
    }

    public ContractItemDTO(ContractItem contractItem) {
        this.id = contractItem.getId();
        this.itemClass = contractItem.getItemClass();
        this.itemName = contractItem.getItemName();
        this.itemMessage = contractItem.getItemMessage();
        this.itemPrice = contractItem.getItemPrice();
        this.images = contractItem.getImages();
        this.chargeTax = contractItem.getChargeTax();
        this.optional = contractItem.getOptional();
        this.userId = contractItem.getUser().getId();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemMessage() {
        return itemMessage;
    }

    public void setItemMessage(String itemMessage) {
        this.itemMessage = itemMessage;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Boolean getChargeTax() {
        return chargeTax;
    }

    public void setChargeTax(Boolean chargeTax) {
        this.chargeTax = chargeTax;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
