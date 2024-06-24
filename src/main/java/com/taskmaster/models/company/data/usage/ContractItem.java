package com.taskmaster.models.company.data.usage;

import com.taskmaster.models.user.AppUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ContractItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemClass; // 'service' or 'product'
    private String itemName;
    private String itemMessage;
    private Double itemPrice;
    @ElementCollection
    private List<String> images;
    private Boolean chargeTax;
    private Boolean optional;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
